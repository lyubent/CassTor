package com.github.lyuben.test;

/**
 * @author lyubentodorov
 * @licence - MIT
 * Available at http://lyuben.herokuapp.com/casstor/ 
 * Source at https://github.com/lyubent/CassTor/ 
 */
public class SchemaTest {
    
    private com.netflix.astyanax.Keyspace keyspace;
        
    public SchemaTest(com.netflix.astyanax.Keyspace keyspace){
        this.keyspace = keyspace;
    }
    
    
    
    /**
     * Test to create a keyspace into Cassandra
     * 
     * @return boolean representing status of the replication factor alteration
     * @throws Exception 
     */
    public boolean incrementReplicationFactorJDBC() throws Exception{
        
        System.out.print("\tTest:\tAltering repliction factor\t");
        
        if(com.github.lyuben.bridge.JDBC.incrementReplicationFactor(keyspace.getKeyspaceName())) {
            System.out.println("...\talteded RF successfully!");
            return true;
        } else {
            System.out.println("...\tfailed to alter RF!");
            throw new Exception();
        }
    }
    
    
    
    /**
     * Test to create a keyspace into Cassandra
     * 
     * @return boolean representing success of retrieving current replication factor
     * @throws Exception 
     */
    public boolean getCurrentReplicationFactorJDBC() throws Exception{
        
        System.out.print("\tTest:\tRetreiving current repliction factor\t");
        
        if(com.github.lyuben.bridge.JDBC.getCurrentReplicationFactor(keyspace.getKeyspaceName()) > 0) {
            System.out.println("...\tretreived RF successfully!");
            return true;
        } else {
            System.out.println("...\tfailed to retreive RF!");
            throw new Exception();
        }
    }
    
    
    
    /**
     * Test to create a keyspace into Cassandra
     * 
     * @return boolean representing success of creating a Keyspace in cassandra
     * @throws Exception 
     */
    public boolean createKS() throws Exception{
        
        System.out.print("\tTest:\tCreating KS\t");
        
        if(com.github.lyuben.bridge.SchemaManager.createKeyspace(keyspace)) {
            System.out.println("...\tcreated KS successfully!");
            return true;
        } else {
            System.out.println("...\tfailed to create KS!");
            throw new Exception();
        }
    }
    
    
    
    /**
     * Test to create a Column Family into Cassandra
     * 
     * @return boolean representing success of creating keyspace in cassandra
     * @throws Exception 
     */
    public boolean createCF() throws Exception{
        
        System.out.print("\tTest:\tCreating CF\t");
        
        if(com.github.lyuben.bridge.SchemaManager.createIndexedColumnFamilyStrings(keyspace)) {
            System.out.println("...\tcreated CF successfully!");
            return true;
        } else {
            System.out.println("...\tfailed to create CF!");
            throw new Exception();
        }
        
    }
    
    
    
    /**
     * Drops an existing column family from Cassandra
     * 
     * @return boolean representing success of deleting column family in cassandra
     * @throws Exception 
     */
    public boolean dropCF() throws Exception{
        
        System.out.print("\tTest:\tDroping CF\t");
        
        if(com.github.lyuben.bridge.SchemaManager.dropColumnFamily(keyspace, 
                com.github.lyuben.bridge.Astyanax.getColumnFamilyStructure())) {
            System.out.println("...\tdroped CF successfully!");
            return true;
        } else {
            System.out.println("...\tfailed to drop CF.");
            throw new Exception();
        }
    }
    
    
    
    /**
     * Drops an existing keyspace from Cassandra
     * 
     * @return boolean representing success of deleting keyspace in cassandra
     * @throws Exception 
     */
    public boolean dropKS() throws Exception{
        
        System.out.print("\tTest:\tDroping KS\t");
        
        if(com.github.lyuben.bridge.SchemaManager.dropKeyspace(keyspace)) {
            System.out.println("...\tdroped KS successfully!");
            return true;
        } else {
            System.out.println("...\tfailed to drop KS!");
            throw new Exception();
        }
    }
}