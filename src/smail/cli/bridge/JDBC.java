package smail.cli.bridge;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;  
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import smail.cli.cql.SchemaCql;

// @author lyubentodorov
// @licence - MIT
// Available at http://lyuben.herokuapp.com/casstor/    
// Source at https://github.com/lyubent/CassTor/ 
//
public class JDBC {
    
    private static final String __SEED__ = "134.36.36.188";
    private static final String __JDBCDriverClassPath__ =
            "org.apache.cassandra.cql.jdbc.CassandraDriver";
    
    // Calls cql update query execution to update the replication factor
    // of a specified keyspace.
    // @return Boolean representing status of the update.
    public static boolean incrementReplicationFactor(String keyspaceName){
        try {
            String query = SchemaCql.updateReplicationFactor( 
                    JDBC.getCurrentReplicationFactor(keyspaceName), keyspaceName);
            
            return jdbcExecCQLUpdate(query, keyspaceName);
        } catch (Exception ex){
            java.util.logging.Logger.getLogger(JDBC.class.getName()).log(java.util.logging.Level.SEVERE, 
                "Failed to alter the replication factor.", ex);
            return false;
        }
    }
    
    
    
    // Finds the current replication factor of the specified keyspace.
    // @return An integer representing the KS replication factor.
    //
    public static int getCurrentReplicationFactor(String keyspaceName){
        try {
            String query = SchemaCql.getCurrentReplicationFactor(keyspaceName);
            ResultSet rs = jdbcExecCQLQuery(query, keyspaceName);
            
            Pattern p = Pattern.compile("\\d+", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(rs.getString(1));
            
            if (m.find()) {
                return  Integer.valueOf(m.group(0));
            }
            
        } catch (Exception ex){
            java.util.logging.Logger.getLogger(JDBC.class.getName()).log(java.util.logging.Level.SEVERE, 
                "Failed to retreive up-to-date replication factor.", ex);
        }
        
        return -1;
    }
    
    
    
    // Executed a CQL query.
    // @return ResultSet storing the data.
    //
    private static ResultSet jdbcExecCQLQuery(String query, String keyspaceName) {
        
        try {
            Class.forName(__JDBCDriverClassPath__);
            Connection con = DriverManager.getConnection(
                "jdbc:cassandra://" + __SEED__ + ":9160/" + keyspaceName);
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            stmt.close();
            con.close();
            
            return rs;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(JDBC.class.getName()).log(java.util.logging.Level.SEVERE, 
                "SQLException while attempting to execute CQL regular query.", ex);
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JDBC.class.getName()).log(java.util.logging.Level.SEVERE, 
                "Failed to find the JDBC driver class", ex);
        }
        
        return null;
    }
    
    
    
    // Executes a CQL update query.
    // @return Boolean representing status of the update.
    //
    private static boolean jdbcExecCQLUpdate(String query, String keyspaceName) {
        
        try {
            Class.forName(__JDBCDriverClassPath__);
            Connection con = DriverManager.getConnection(
                "jdbc:cassandra://" + __SEED__ + ":9160/" + keyspaceName);
            
            PreparedStatement statement = con.prepareStatement(query);
            statement.executeUpdate();
            statement.close();
            con.close();
            
            return true;
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(JDBC.class.getName()).log(java.util.logging.Level.SEVERE, 
                "SQLException while attempting to execute CQL update query.", ex);
        }
        
        return false;
    }

    
}
