package smail.cli.test;

import com.netflix.astyanax.Keyspace;

public class SchemaCreationTest {
    
    private Keyspace keyspace;
    private static final String __SEEDS__ = "134.36.36.188";
    private static final String __CLUSTER__ = "Test Cluster";
    private static final String __KEYSPACE__ = "TestKS";
    private static final String __USER__ = "TestUsr";
    
    public SchemaCreationTest(){
        keyspace = getTestAstyanaxContext();
    }
    
    public void runTest() throws Exception{
        
                
        //junit.framework.Assert.assertTrue(createNetKS());
    }
    
    public boolean createKS(){
        return smail.cli.astyanax.Schema.createKeyspace(keyspace);
    }
    
    //creates column family to be droped
    public boolean createCF() throws Exception{
        return smail.cli.astyanax.Schema.createIndexedColumnFamilyStrings(keyspace);
    }
    
    public boolean insertEmail(Keyspace ks){
        return smail.cli.astyanax.Astyanax.insertEmail(keyspace, __USER__, 
                java.util.Arrays.asList("Receiver", "Subject", "Body"));
    }
    
    public boolean dropCF(){
        return smail.cli.astyanax.Schema.dropColumnFamily
                (keyspace, smail.cli.astyanax.Astyanax.getColumnFamilyStructure());
    }
    
    public boolean dropKS(){
        return smail.cli.astyanax.Schema.dropKeyspace(keyspace);
    }
    
    public boolean deleteEmail(){
        return true;
    }
    
    //Test context, we dont want to mess with the live data when testing.
    private Keyspace getTestAstyanaxContext(){
        
        com.netflix.astyanax.AstyanaxContext<Keyspace> context = 
                new com.netflix.astyanax.AstyanaxContext.Builder()
        .forCluster(__CLUSTER__)
        .forKeyspace(__KEYSPACE__) //NetworkKS
        .withAstyanaxConfiguration(
         new com.netflix.astyanax.impl.AstyanaxConfigurationImpl()      
        .setDiscoveryType(com.netflix.astyanax.connectionpool.NodeDiscoveryType.NONE) // NONE FOR BASIK KS
        .setCqlVersion("3.0.0")) //using CQL3 (fails, its still CQL2)
        .withConnectionPoolConfiguration(
         new com.netflix.astyanax.connectionpool.impl.ConnectionPoolConfigurationImpl("MyConnectionPool")
        .setPort(9160)
        .setMaxConnsPerHost(10)
        .setSeeds(__SEEDS__))
        .withConnectionPoolMonitor(
         new com.netflix.astyanax.connectionpool.impl.Slf4jConnectionPoolMonitorImpl())
        .buildKeyspace(com.netflix.astyanax.thrift.ThriftFamilyFactory.getInstance());
        
        context.start();
        Keyspace ks = context.getEntity();
        
        //returns Keyspace obj
        return context.getEntity();
    }
}