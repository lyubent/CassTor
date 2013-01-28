/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smail.cli.test;

import com.netflix.astyanax.Keyspace;
import java.util.logging.Level;
import java.util.logging.Logger;
import smail.cli.Main;

/**
 *
 * @author lyubentodorov
 */
public class TestSuite {
    
    private static final String __SEEDS__ = "134.36.36.188";
    private static final String __CLUSTER__ = "Test Cluster";
    private static final String __KEYSPACE__ = "TestKS";
    
    
    
    public void runTests(){
        SchemaTest schemaTester = new SchemaTest(getTestAstyanaxContext());
        QueryingTest queryTester = new QueryingTest(getTestAstyanaxContext());
        
        try {
//            
//            schemaTester.createKS();
//            System.out.println("Created KS");
//            
//            schemaTester.createCF();
//            System.out.println("Created CF");
//            
//            //insertion/deletion tests
//            String key = queryTester.insertEmail();
//            System.out.println("Inserting email, KEY=" + key);
//
//            //String key = "TestUsr@1359412341";
//            queryTester.deleteEmail(key);
//            System.out.println("Deleting email");

            schemaTester.dropCF(); 
            System.out.println("Droped CF");
            
            schemaTester.dropKS();
            System.out.println("Droped KS");
            
        } catch(Exception ex){
            Logger.getLogger(TestSuite.class.getName()).log(Level.SEVERE, null, ex);
        }
        //junit.framework.Assert.assertTrue(createNetKS());
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
