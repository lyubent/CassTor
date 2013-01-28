/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smail.cli.test;

import com.google.common.collect.ImmutableMap;
import com.netflix.astyanax.AstyanaxContext;
import com.netflix.astyanax.Keyspace;
import com.netflix.astyanax.MutationBatch;
import com.netflix.astyanax.connectionpool.NodeDiscoveryType;
import com.netflix.astyanax.connectionpool.OperationResult;
import com.netflix.astyanax.connectionpool.exceptions.ConnectionException;
import com.netflix.astyanax.connectionpool.impl.ConnectionPoolConfigurationImpl;
import com.netflix.astyanax.connectionpool.impl.Slf4jConnectionPoolMonitorImpl;
import com.netflix.astyanax.impl.AstyanaxConfigurationImpl;
import com.netflix.astyanax.model.ColumnFamily;
import com.netflix.astyanax.serializers.StringSerializer;
import com.netflix.astyanax.thrift.ThriftFamilyFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import smail.cli.Main;
import smail.cli.util.DateUtil;
/**
 *
 * @author lyubentodorov
 */
public class SchemaCreationTest {
    
    private Keyspace keyspace;
    
    public SchemaCreationTest(Keyspace keyspace){
        this.keyspace = keyspace;
    }
    
    public  boolean createNetKS(){
        return smail.cli.astyanax.Schema.createNetworkKeyspace(keyspace);
    }
    
    public  boolean createCF(){
        return smail.cli.astyanax.Schema.createIndexedColumnFamilyStrings(keyspace);
    }
    
    public boolean dropCF(){
        return smail.cli.astyanax.Schema.dropColumnFamily(keyspace, smail.cli.astyanax.Astyanax.getColumnFamilyStructure());
    }
    
//    public  boolean insertEncryptedData(){
//        
//        return smail.cli.astyanax.Astyanax.insertEmail(keyspace, "ltodorov", null);
//    }
    
    public  boolean insertEmail(Keyspace ks){
        
//        return smail.cli.astyanax.Astyanax.insertEmail(ks, "Tester", 
//                java.util.Arrays.asList("Tester","Test subject","Test body"));

        ColumnFamily<String, String> mail = new ColumnFamily<>( "MESSAGE", // CF Name
            StringSerializer.get(), StringSerializer.get());

        MutationBatch mbatch = ks.prepareMutationBatch();

        String wiki = "You can use the cqlsh commands described in this section to create a keyspace. In creating an example keyspace for Twissandra, we will assume a desired replication factor of 3 and implementation of the NetworkTopologyStrategy replica placement strategy. For more information on these keyspace options, see About Replication in Cassandra.";
        String wiki2 = "Installing Thrift - All CQL drivers require Thrift 0.6. If you run CQL commands on a Cassandra 0.8 node, the required Thrift jar files are already present in the environment. However, to run CQL commands from a remote machine, you must install Thrift 0.6 as described in the Cassandra Wiki.";
        
        mbatch.withRow(mail, String.valueOf("TesterOUT@" + DateUtil.getUnixTimestamp()))
            .putColumn("RECEIVER", "TesterIn", null)
            .putColumn("SENDER", "TesterOUT", null)
            .putColumn("SUBJECT", "Subj", null)
            .putColumn("BODY", "Message bodeh", null)
            .putColumn("DELETED_SENDER", "No", null)
            .putColumn("DELETED_RECEIVER", "No", null)
            .putColumn("DATE", DateUtil.getCurrentDate(), null);
        
        try { 
            OperationResult<Void> result = mbatch.execute();
            result.toString();
            return true;
        }
        catch (ConnectionException ex) { 
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    // Runs the tests
    //
    //
    public void runTest() throws ConnectionException{
        
//        AstyanaxContext<Keyspace> context = new AstyanaxContext.Builder()
//        .forCluster("Test Cluster")
//        .forKeyspace("NetworkKS")
//        .withAstyanaxConfiguration(new AstyanaxConfigurationImpl()      
//        .setDiscoveryType(NodeDiscoveryType.NONE))
//        .withConnectionPoolConfiguration(new ConnectionPoolConfigurationImpl("MyConnectionPool")
//        .setPort(9160)
//        .setMaxConnsPerHost(10)
//        .setInitConnsPerHost(1)
//        .setSeeds("134.36.36.188"))
//        .withConnectionPoolMonitor(new Slf4jConnectionPoolMonitorImpl())
//        .buildKeyspace(ThriftFamilyFactory.getInstance());
        
        
        AstyanaxContext<Keyspace> context = new AstyanaxContext.Builder()
        .forCluster("Test Cluster")
        .forKeyspace("NetworkKS")
        .withAstyanaxConfiguration(new AstyanaxConfigurationImpl()      
        .setDiscoveryType(NodeDiscoveryType.NONE) // NONE FOR BASIK KS
        .setCqlVersion("3.0.0")) //using CQL3 (fails, its still CQL2)
        .withConnectionPoolConfiguration(new ConnectionPoolConfigurationImpl("MyConnectionPool")
        .setPort(9160)
        .setMaxConnsPerHost(10)
        .setSeeds("134.36.36.188"))
        .withConnectionPoolMonitor(new Slf4jConnectionPoolMonitorImpl())
        .buildKeyspace(ThriftFamilyFactory.getInstance());
        
        context.start();
        Keyspace ks = context.getEntity();
        
        //NETKS
//        ks.createKeyspace(ImmutableMap.<String, Object>builder()
//                .put("strategy_options", ImmutableMap.<String, Object>builder()
//                .put("us-east", "3")
//                .put("eu-west", "3").build())
//                .put("strategy_class", "NetworkTopologyStrategy").build());
        
        //Normal KS - currently using this one
//        ks.createKeyspace(ImmutableMap.<String, Object>builder()
//                .put("strategy_options", ImmutableMap.<String, Object>builder()
//                .put("replication_factor", "2").build())
//                .put("strategy_class","SimpleStrategy").build());
        
        System.out.println("\n\n\n\n\n\n\n\n\n");
//        createNetKS(); //works
//        createCF(); //works
        //insertEmail(ks);
                
//        junit.framework.Assert.assertTrue(createNetKS());
//        junit.framework.Assert.assertTrue(createCF());
//        junit.framework.Assert.assertTrue(insertEmail());
    }
}