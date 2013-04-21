package com.github.lyuben.bridge;

import com.google.common.collect.ImmutableMap;
import com.netflix.astyanax.Keyspace;
import com.netflix.astyanax.connectionpool.exceptions.ConnectionException;
import com.netflix.astyanax.model.ColumnFamily;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author lyubentodorov
 * @licence - MIT
 * Available at http://lyuben.herokuapp.com/casstor/ 
 * Source at https://github.com/lyubent/CassTor/ 
 */
public class SchemaManager {
    
    
    /**
     * Sets up the database for use by adding the CASSTOR keyspace and adding the __COLUMNFAMILY__
     * keyspace. (look at createIndexedColumnFamilyStrings for details of structure)
     * THIS METHOD SHOULD ONLY BE RUN ONCE FOR THE ENTIRE LIFE OF THE CLUSTER!
     * @param keyspace
     * @return boolean
     */
    public static boolean buildSchema(Keyspace keyspace){
        try {
            SchemaManager.createKeyspace(keyspace); //OK
            SchemaManager.createIndexedColumnFamilyStrings(keyspace);
        } catch (Exception ex) {
            Logger.getLogger(SchemaManager.class.getName()).log(Level.SEVERE, 
                    "Failed to build Schema.", ex);
            return false;
        }
        
        return true;
    }
    
    
    
    /**
     * Remove the database and drop the __COLUMNFAMILY__ keyspace. 
     * SHOULD ONLY BE RUN ONCE FOR THE ENTIRE LIFE OF THE CLUSTER 
     * (presumably when you want to kill it!)
     * 
     * @param keyspace
     * @return boolean
     */
    public static boolean destroySchema(Keyspace keyspace){
        try {
            //drop CF
            SchemaManager.dropColumnFamily(keyspace, Astyanax.getColumnFamilyStructure());
            //drop KS
            SchemaManager.dropKeyspace(keyspace);
        } catch (Exception ex) {
            Logger.getLogger(SchemaManager.class.getName()).log(Level.SEVERE, 
                    "Failed to destroy the Schema.", ex);
            return false;
        }
        
        return true;
    }
    
    
    
    /**
     * Creates a CF with 3 indexed
     * @param keyspace from an astyanax context initialized with KS name
     * @return 
     */
    public static boolean createIndexedColumnFamilyStrings(Keyspace keyspace){
        try {
            keyspace.createColumnFamily(Astyanax.getColumnFamilyStructure(), ImmutableMap.<String, Object>builder()
            .put("default_validation_class", "UTF8Type")
            .put("key_validation_class", "UTF8Type")
            .put("comparator_type", "UTF8Type")
            .put("column_metadata", ImmutableMap.<String, Object>builder()
                    // Index1 - receiver of the message
                    .put("RECEIVER", ImmutableMap.<String, Object>builder()
                        .put("validation_class", "UTF8Type")
                        .put("index_name", "RECEIVER")
                        .put("index_type", "KEYS")
                        .build()) // build index1
                    // Index2 - sender of the message
                    .put("SENDER", ImmutableMap.<String, Object>builder()
                        .put("validation_class", "UTF8Type")
                        .put("index_name", "SENDER")
                        .put("index_type", "KEYS")
                        .build()) // build index2
                    // Index3 - delted by user (whom received the email)
                    .put("DELETED_RECEIVER", ImmutableMap.<String, Object>builder()
                        .put("validation_class", "UTF8Type")
                        .put("index_name", "DELETED_RECEIVER")
                        .put("index_type", "KEYS")
                        .build()) // build index3
                    // Index4 - deleted by sender
                    .put("DELETED_SENDER", ImmutableMap.<String, Object>builder()
                        .put("validation_class", "UTF8Type")
                        .put("index_name", "DELETED_SENDER")
                        .put("index_type", "KEYS")
                        .build()) // build index3
                 .build()) // build meta-data
             .build()); // build CF
            
        } catch (ConnectionException ex) { 
            Logger.getLogger(SchemaManager.class.getName()).log(Level.SEVERE, 
                    "Failed to create Indexed Column Family of strings.", ex);
            return false; //err
        }
        
        return true; // Sucess
    }
    
    
    
    /**
     * Creates a keyspace from an astyanax context
     * @param keyspace from an astyanax context initialiased with KS name
     * @return 
     */
    public static boolean createKeyspace(Keyspace keyspace){
        try {
            keyspace.createKeyspace(ImmutableMap.<String, Object>builder()
                .put("strategy_options", ImmutableMap.<String, Object>builder()
                .put("replication_factor", "3").build())
                .put("strategy_class","SimpleStrategy").build());
            return true; // Success
            
        } catch (ConnectionException ex) {
            Logger.getLogger(SchemaManager.class.getName()).log(Level.SEVERE, 
                    "Failed to create Keyspace", ex);
        }
        
        return false;
    }
    
    
    
    /**
     * Drops an unwanted column family
     * @param keyspace Requires a keyspace object
     * @param CF_COMPOSITE the column family structure of CF to be dropped
     * @return 
     */
    public static boolean dropColumnFamily(Keyspace keyspace, ColumnFamily<String, String> CF_COMPOSITE){
        
        try {
            keyspace.dropColumnFamily(CF_COMPOSITE);
        } catch (ConnectionException ex) {
            Logger.getLogger(SchemaManager.class.getName()).log(Level.SEVERE, 
                    "Failed to drop Column Family", ex);
            return false;
        }
        
        return true;
    }
    
    
    
    /**
     * Drops an unused / unwanted keyspace
     * @param keyspace Requires a keyspace object storing the KS to be dropped
     * @return 
     */
    public static boolean dropKeyspace(Keyspace keyspace){
        
        try {
            keyspace.dropKeyspace();
        } catch (Exception ex) {
            Logger.getLogger(SchemaManager.class.getName()).log(Level.SEVERE, 
                    "Failed to drop keyspace", ex);
            return false;
        }
        
        return true;
    }
}
