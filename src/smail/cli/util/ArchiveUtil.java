package smail.cli.util;

import java.io.File;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.cassandra.io.util.FileUtils;


// @author lyubentodorov
// @licence - MIT
// Available at http://lyuben.herokuapp.com/casstor/ 
// Source at https://github.com/lyubent/CassTor/ 
//
public class ArchiveUtil {
    
    private static final String __DESKTOPLOCATION__ = 
            System.getProperty("user.home") + "/Desktop/";
    private static final String __CASSANDRASERVLOCATION__ = 
            System.getProperty("user.home") + "/Desktop/cassandra/bin/";
    
    public ArchiveUtil() {
        
    }
    
    
    // Unzips the cassandra server
    //
    public static void unzip(){
        String source = "cassandra.zip";
        String destination = __DESKTOPLOCATION__;
        String password = "password";

        try {
            ZipFile zipFile = new ZipFile(source);
            zipFile.extractAll(destination);
            //FileUtils.deleteRecursive(new File(__DESKTOPLOCATION__ + "__MACOSX"));
        } catch (ZipException ex) {
            Logger.getLogger(ArchiveUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    // Setting up the correct permissions as ZIP files cannot keep file permissions.
    //
    public static void setExecPermissions() {
        
        java.util.List<File> executableFiles = Arrays.asList(
        new File(__CASSANDRASERVLOCATION__ + "cassandra"),
        new File(__CASSANDRASERVLOCATION__ + "cassandra-cli"),
        new File(__CASSANDRASERVLOCATION__ + "cqlsh"),
        new File(__CASSANDRASERVLOCATION__ + "debug-cql"),
        new File(__CASSANDRASERVLOCATION__ + "json2sstable"),
        new File(__CASSANDRASERVLOCATION__ + "nodetool"),
        new File(__CASSANDRASERVLOCATION__ + "shuffle"),
        new File(__CASSANDRASERVLOCATION__ + "sstable2json"),
        new File(__CASSANDRASERVLOCATION__ + "sstablekeys"),
        new File(__CASSANDRASERVLOCATION__ + "sstableloader"),
        new File(__CASSANDRASERVLOCATION__ + "sstablescrub"),
        new File(__CASSANDRASERVLOCATION__ + "stop-server"));
        
        for(File cassandraFile : executableFiles) {
            cassandraFile.setExecutable(true);
        }
    }
    
}
