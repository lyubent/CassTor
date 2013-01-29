package smail.cli.test;

import com.netflix.astyanax.Keyspace;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public boolean createKS(){
        
        boolean stats = false;
        System.out.print("\tTest:\tCreating KS\t");
        
        try{
            stats = smail.cli.astyanax.Schema.createKeyspace(keyspace);
            System.out.println("...\tcreated KS successfully!");
        } catch (Exception ex) {
            Logger.getLogger(SchemaTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("...\tfailed to create KS!");
            return stats;
        }
    }
    
    
    
    // Tested and working - 28 Jan 2013
    // Test to createa a Column Family into Cassandra
    //
    public boolean createCF() throws Exception{
        
        boolean stats = false;
        System.out.print("\tTest:\tCreating CF\t");
        
        try {
            stats = smail.cli.astyanax.Schema.createIndexedColumnFamilyStrings(keyspace);
            System.out.println("...\tcreated CF successfully!");
        } catch (Exception ex) {
            Logger.getLogger(SchemaTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("...\tfailed to create CF!");
            return stats;
        }
    }
    
    
    
    // Tested and working - 28 Jan 2013
    // Drops an existing column family from Cassandra
    //
    public boolean dropCF(){
        
        boolean stats = false;
        System.out.print("\tTest:\tDroping CF\t");
        
        try {
            stats = smail.cli.astyanax.Schema.dropColumnFamily
                (keyspace, smail.cli.astyanax.Astyanax.getColumnFamilyStructure());
            System.out.println("...\tdroped CF successfully!");
        } catch (Exception ex) {
            Logger.getLogger(SchemaTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("...\tfailed to drop CF.");
            return stats;
        }
    }
    
    
    
    // Tested and working - 28 Jan 2013
    // Drops an existing keyspace from Cassandra
    //
    public boolean dropKS(){
        
        boolean stats = false;
        System.out.print("\tTest:\tDroping KS\t");
        
        try {
            stats = smail.cli.astyanax.Schema.dropKeyspace(keyspace);
            System.out.println("...\tdroped KS successfully!");
        } catch (Exception ex){
            Logger.getLogger(SchemaTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("...\tfailed to drop KS!");
            return stats;
        }
    }
}