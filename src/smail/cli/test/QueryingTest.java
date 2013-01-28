package smail.cli.test;

import com.netflix.astyanax.Keyspace;

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
    
    public String insertEmail(){
        return smail.cli.astyanax.Astyanax.insertEmail(keyspace, __USER__, 
                java.util.Arrays.asList("Receiver", "Subject", "Body"));
    }
    
    public boolean deleteEmail(String __KEY__){
        return smail.cli.astyanax.Astyanax.deleteEmail(keyspace, __KEY__);
    }
}
