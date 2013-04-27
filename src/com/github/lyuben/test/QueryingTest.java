package com.github.lyuben.test;

import com.netflix.astyanax.Keyspace;

/**
 * @author lyubentodorov
 * @licence - MIT
 * Available at http://lyuben.herokuapp.com/casstor/ 
 * Source at https://github.com/lyubent/CassTor/ 
 */
public class QueryingTest {
    
    private Keyspace keyspace;
    private static final String __USER__ = "TestUsr";
    
    public QueryingTest(Keyspace keyspace){
        this.keyspace = keyspace;
    }
    
    
    
    /**
     * Test to insert an email into Cassandra
     * 
     * @return String representing value of the key for the newly inserted email. NULL implies failure.
     * @throws Exception 
     */
    public String insertEmail() throws Exception{
        
        String key = "key" + String.valueOf(System.currentTimeMillis()).substring(0, 2);
        System.out.print("\tTest:\tInserting email\t");
        
        
        key = com.github.lyuben.bridge.Astyanax.insertEmail(keyspace, __USER__, 
            java.util.Arrays.asList("Receiver", "Subject", "Body"));
            
        if(!key.equals("")) {
            System.out.println("...\tinserted email successfully! KEY=" + key);
            return key;
        } else {
            System.out.println("...\tfailed to insert email!");
            throw new Exception();
        }
    }
    
    
    
    /**
     * Test to delete an email from Cassandra
     * 
     * @param __KEY__ - The unique identifier of an email
     * @return boolean representing status of deleting an email.
     * @throws Exception 
     */
    public boolean deleteEmail(String __KEY__) throws Exception {
        
        System.out.print("\tTest:\tDeleting email\t");
        
        //Yes cassandra is infact so fast we need to slow stuff down a bit.
        Thread.sleep(1000);
        
        if(com.github.lyuben.bridge.Astyanax.deleteEmail(keyspace, __KEY__)) {
            System.out.println("...\tdeleted successfully!");
            return true;
        } else {
            System.out.println("...\tfailed to delete email!");
            throw new NullPointerException(); //throw null, test failed.
        }
    }
}
