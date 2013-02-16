package smail.cli.test;

import com.netflix.astyanax.Keyspace;

// @author lyubentodorov
// @licence - MIT
// Available at http://lyuben.herokuapp.com/casstor/ 
// Source at https://github.com/lyubent/CassTor/ 
//
public class SchemaTest {
    
    private Keyspace keyspace;
        
    public SchemaTest(Keyspace keyspace){
        this.keyspace = keyspace;
    }
    
    
    
    // Test to create a keyspace into Cassandra
    // Tested and working - 28 Jan 2013
    //
    public boolean createKS() throws Exception{
        
        System.out.print("\tTest:\tCreating KS\t");
        
        if(smail.cli.astyanax.Schema.createKeyspace(keyspace)) {
            System.out.println("...\tcreated KS successfully!");
            return true;
        } else {
            System.out.println("...\tfailed to create KS!");
            throw new Exception();
        }
    }
    
    
    
    // Tested and working - 28 Jan 2013
    // Test to createa a Column Family into Cassandra
    //
    public boolean createCF() throws Exception{
        
        System.out.print("\tTest:\tCreating CF\t");
        
        if(smail.cli.astyanax.Schema.createIndexedColumnFamilyStrings(keyspace)) {
            System.out.println("...\tcreated CF successfully!");
            return true;
        } else {
            System.out.println("...\tfailed to create CF!");
            throw new Exception();
        }
        
    }
    
    
    
    // Tested and working - 28 Jan 2013
    // Drops an existing column family from Cassandra
    //
    public boolean dropCF() throws Exception{
        
        System.out.print("\tTest:\tDroping CF\t");
        
        if(smail.cli.astyanax.Schema.dropColumnFamily(keyspace, 
                smail.cli.astyanax.Astyanax.getColumnFamilyStructure())) {
            System.out.println("...\tdroped CF successfully!");
            return true;
        } else {
            System.out.println("...\tfailed to drop CF.");
            throw new Exception();
        }
    }
    
    
    
    // Tested and working - 28 Jan 2013
    // Drops an existing keyspace from Cassandra
    //
    public boolean dropKS() throws Exception{
        
        System.out.print("\tTest:\tDroping KS\t");
        
        if(smail.cli.astyanax.Schema.dropKeyspace(keyspace)) {
            System.out.println("...\tdroped KS successfully!");
            return true;
        } else {
            System.out.println("...\tfailed to drop KS!");
            throw new Exception();
        }
    }
}