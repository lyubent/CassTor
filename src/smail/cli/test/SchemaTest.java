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
    
    //Tested and working - 28 Jan 2013
    public boolean createKS(){
        return smail.cli.astyanax.Schema.createKeyspace(keyspace);
    }
    
    //Tested and working - 28 Jan 2013
    public boolean createCF() throws Exception{
        return smail.cli.astyanax.Schema.createIndexedColumnFamilyStrings(keyspace);
    }
    
    //Tested and working - 28 Jan 2013
    public boolean dropCF(){
        return smail.cli.astyanax.Schema.dropColumnFamily
                (keyspace, smail.cli.astyanax.Astyanax.getColumnFamilyStructure());
    }
    
    //Tested and working - 28 Jan 2013
    public boolean dropKS(){
        return smail.cli.astyanax.Schema.dropKeyspace(keyspace);
    }
}