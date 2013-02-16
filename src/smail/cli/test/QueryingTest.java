package smail.cli.test;

import com.netflix.astyanax.Keyspace;
import java.util.logging.Level;
import java.util.logging.Logger;

// @author lyubentodorov
// @licence - MIT
// Available at http://lyuben.herokuapp.com/casstor/ 
// Source at https://github.com/lyubent/CassTor/ 
//
public class QueryingTest {
    
    private Keyspace keyspace;
    private static final String __USER__ = "TestUsr";
    
    public QueryingTest(Keyspace keyspace){
        this.keyspace = keyspace;
    }
    
    
    
    // Test to insert an email into Cassandra
    // Tested and working - 29 Jan 2013
    //
    public String insertEmail() throws Exception{
        
        String key = "key" + String.valueOf(System.currentTimeMillis()).substring(0, 2);
        System.out.print("\tTest:\tInserting email\t");
        
        
        key = smail.cli.astyanax.Astyanax.insertEmail(keyspace, __USER__, 
            java.util.Arrays.asList("Receiver", "Subject", "Body"));
            
        if(!key.equals("")) {
            System.out.println("...\tinserted email successfully! KEY=" + key);
            return key;
        } else {
            System.out.println("...\tfailed to insert email!");
            throw new Exception();
        }
    }
    
    
    
    // Test to delete an email from Cassandra
    // Tested and working - 29 Jan 2013
    //
    public boolean deleteEmail(String __KEY__) throws Exception {
        
        System.out.print("\tTest:\tDeleting email\t");
        
        //Yes cassandra is infact so fast we need to slow stuff down a bit.
        Thread.sleep(1000);
        
        if(smail.cli.astyanax.Astyanax.deleteEmail(keyspace, __KEY__)) {
            System.out.println("...\tdeleted successfully!");
            return true;
        } else {
            System.out.println("...\tfailed to delete email!");
            throw new NullPointerException(); //throw null, test failed.
        }
    }
}
