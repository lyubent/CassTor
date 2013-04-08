package com.github.lyuben.bridge;

import com.github.lyuben.cql.EmailCql;
import com.github.lyuben.cql.SchemaCql;
import com.github.lyuben.tor.Anonymizer;
import com.github.lyuben.util.Base64Crypto;
import com.github.lyuben.util.DateUtil;
import com.github.lyuben.util.EmailFormatter;
import com.netflix.astyanax.Keyspace;
import com.netflix.astyanax.MutationBatch;
import com.netflix.astyanax.connectionpool.OperationResult;
import com.netflix.astyanax.connectionpool.exceptions.ConnectionException;
import com.netflix.astyanax.model.Column;
import com.netflix.astyanax.model.ColumnFamily;
import com.netflix.astyanax.model.ColumnList;
import com.netflix.astyanax.model.CqlResult;
import com.netflix.astyanax.model.Row;
import com.netflix.astyanax.serializers.BytesArraySerializer;
import com.netflix.astyanax.serializers.IntegerSerializer;
import com.netflix.astyanax.serializers.StringSerializer;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringEscapeUtils;

/**
 * @author lyubentodorov
 * @licence - MIT
 * Available at http://lyuben.herokuapp.com/casstor/ 
 * Source at https://github.com/lyubent/CassTor/ 
 */
public class Astyanax {
    
    private static final String __KEYSPACE__ = "MAIL_KS";//"KeyspaceName";
    private static final String __COLUMNFAMILY__ = "MESSAGE_CF";//"KeyspaceName";
    private static final String __CLUSTER__ = "Test Cluster";
    private static final String __SEEDS__ = "134.36.36.188";
    //private static final String __SEEDS__ = "127.0.0.1:9160";
    
    
    /**
     * Accessor to simplify passing ks name around
     * @return String representing keyspace name
     */
    public static String getKSName() {
        return __KEYSPACE__;
    }
    
    
    
    /**
     * Executes CQL3 statements and returns the results
     * @param keyspace
     * @param cqlStatement
     * @return OperationResult<CqlResult<String, String>>
     */
    public static OperationResult<CqlResult<String, String>> execCQL(Keyspace keyspace, String cqlStatement){
        try {
            
            ColumnFamily<String, String> mail = new ColumnFamily<String, String>(
            keyspace.getKeyspaceName(), // CF Name
            StringSerializer.get(),   // Key Serializer
            StringSerializer.get());  // Column Serializer
            
            OperationResult<CqlResult<String, String>> result = 
                    keyspace.prepareQuery(mail).withCql(cqlStatement).execute();
            
            return result;
               
        } catch (ConnectionException ex) { 
            new Anonymizer().fallbackToTPC();
            Logger.getLogger(Astyanax.class.getName()).log(Level.SEVERE, 
                    "Error during CQL Execution", ex);
        }
        
        return null;
    }
    
    
    
    /**
     * Processes the CQL3 result object displaying it's fiels
     * @param result requires a CqlResult object
     * @return A list of Strings representing results from a query.
     */
    public static List<String> processResults(OperationResult<CqlResult<String, String>> result){
        try {
        List messageList = new LinkedList();
        
        for (Row <String, String> row : result.getResult().getRows()) {
            
            String key = "", body = "", subject = "", from = "", date = "";
            for (Column<String> column : row.getColumns()){
                if(column.getName().equals("KEY")) {
                    key = column.getValue(new StringSerializer());
                }
                if(column.getName().equals("BODY")) {
                    body = column.getValue(new StringSerializer());
                }
                if(column.getName().equals("SUBJECT")) {
                    subject = column.getValue(new StringSerializer());
                }
                if(column.getName().equals("SENDER")) {
                    from = column.getValue(new StringSerializer());
                }
                if(column.getName().equals("DATE")) {
                    date = column.getValue(new StringSerializer());
                }
            }
            
            messageList.add(EmailFormatter.structureMessage(key, body, from, date, subject));
        }
        
        return messageList; } catch (Exception ex ){
            Logger.getLogger(Astyanax.class.getName()).log(Level.SEVERE, 
                    "Error loading emails. Fallback initiated.", ex);
            new Anonymizer().fallbackToTPC();
            
        }
        
        return null;
    }
    
    
    
    /**
     * Inserts a dynamic range of colums specified bellow
     * @param keyspace Requires a keyspace context
     * @param user the user's username and a 
     * @param uinput list of input 
     * @return 
     */
    public static String insertEmail(Keyspace keyspace, String user, List<String> uinput){
    //DISONE    
        String __KEY__ = String.valueOf(user + "@" + DateUtil.getUnixTimestamp());
        ColumnFamily<String, String> mail = 
            new ColumnFamily<String, String>( __COLUMNFAMILY__, // CF Name
            StringSerializer.get(), StringSerializer.get());
        MutationBatch mbatch = keyspace.prepareMutationBatch();

        mbatch.withRow(mail, __KEY__)
            .putColumn("RECEIVER", Base64Crypto.encode(uinput.get(0)), null)
            .putColumn("SENDER", Base64Crypto.encode(user), null)
            .putColumn("SUBJECT", StringEscapeUtils.escapeJava(Base64Crypto.encode(uinput.get(1))), null)
            .putColumn("BODY", StringEscapeUtils.escapeJava(Base64Crypto.encode(uinput.get(2))), null)
            .putColumn("DELETED_SENDER", Base64Crypto.encode("No"), null)
            .putColumn("DELETED_RECEIVER", Base64Crypto.encode("No"), null)
            .putColumn("DATE", Base64Crypto.encode(DateUtil.getCurrentDate()), null);
        
        try { 
            OperationResult<Void> result = mbatch.execute();
            result.toString();
        }
        catch (Exception ex) { 
            Logger.getLogger(Astyanax.class.getName()).log(Level.SEVERE, 
                    "Error inserting email.", ex);
            return "";
        }
        
        return __KEY__;
    }
    
    
    
    /**
     * Deletes an email by Key
     * @param keyspace Keyspace object 
     * @param __KEY__ UID Key of the email
     * @return status of deletion.
     */
    public static boolean deleteEmail(Keyspace keyspace, String __KEY__){
        
       if(Astyanax.execCQL(keyspace, EmailCql.hardDeleteEmail(__KEY__)) == null) {
            return false;
        }
        
        return true;
    }
    
    
    
    /**
     * Builds an Astyanax context
     * @return The keyspace within the context
     */
    public static Keyspace getKeyspaceContext(){
        //Normal KS
        com.netflix.astyanax.AstyanaxContext<Keyspace> context = 
         new com.netflix.astyanax.AstyanaxContext.Builder()
        .forCluster(__CLUSTER__)
        .forKeyspace(__KEYSPACE__) //NetworkKS
        .withAstyanaxConfiguration(
         new com.netflix.astyanax.impl.AstyanaxConfigurationImpl()      
        .setDiscoveryType(com.netflix.astyanax.connectionpool.NodeDiscoveryType.NONE) // NONE FOR BASIK KS
        .setCqlVersion("3.0.0")) //using CQL3
                
        .withConnectionPoolConfiguration(
         new com.netflix.astyanax.connectionpool.impl.ConnectionPoolConfigurationImpl("MyConnectionPool")
        .setPort(9160)
        //If queries take longer than a 10 seconds, timeout.
        .setConnectTimeout(10000)
        .setMaxConnsPerHost(10)
        .setSeeds(__SEEDS__))
        .withConnectionPoolMonitor(
         new com.netflix.astyanax.connectionpool.impl.Slf4jConnectionPoolMonitorImpl())
        .buildKeyspace(com.netflix.astyanax.thrift.ThriftFamilyFactory.getInstance());
        
        context.start();
        
        return context.getEntity();
    }
    
    
    
    /**
     * Builds the CF structure for the MESSAGE column family
     * @param colFamilyName
     * @return ColumnFamily<String, String> 
     */
    public static ColumnFamily<String, String> getColumnFamilyStructure(String colFamilyName){
        return ColumnFamily.newColumnFamily(colFamilyName, //default should be message 
                StringSerializer.get(), StringSerializer.get());
    }
    
    
    
    /**
     * Builds the CF structure for the MESSAGE column family
     * @return ColumnFamily<String, String> 
     */
    public static ColumnFamily<String, String> getColumnFamilyStructure(){
        return ColumnFamily.newColumnFamily(__COLUMNFAMILY__, //default should be message 
                StringSerializer.get(), StringSerializer.get());
    }
}
