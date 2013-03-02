package com.github.lyuben.cql;

// @author lyubentodorov
// @licence - MIT
// Available at http://lyuben.herokuapp.com/casstor/    
// Source at https://github.com/lyubent/CassTor/ 
//
public class SchemaCql {
    private static final String __COLUMNFAMILY__ = "MESSAGE_CF";
    
    // Builds CQL for retreiving all emails that haven't been deleted
    //
    public static String updateReplicationFactor(int newReplicationFactor, String keyspaceName){
        return "ALTER KEYSPACE \"" + keyspaceName + "\" WITH REPLICATION = { " + 
               "'class' : 'SimpleStrategy', 'replication_factor' : " + (2) + " };";
    }
    
    
    // Builds CQL for retreiving all emails that haven't been deleted
    //
    public static String getCurrentReplicationFactor(String keyspace){
        return "SELECT strategy_options " + 
               "FROM system.schema_keyspaces " + 
               "WHERE keyspace_name = '" + keyspace + "';";
    }
}
