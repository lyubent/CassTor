package com.github.lyuben.util;

import java.io.File;
import java.util.Arrays;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;


/**
 * @author lyubentodorov
 * @licence - MIT
 * Available at http://lyuben.herokuapp.com/casstor/ 
 * Source at https://github.com/lyubent/CassTor/ 
 */
public class ArchiveUtil {
    
    private static final String __DESKTOPLOCATION__ = 
            System.getProperty("user.home") + "/Desktop/";
    private static final String __CASSANDRASERVLOCATION__ = 
            System.getProperty("user.home") + "/Desktop/cassandra/";
    
    public ArchiveUtil() {
        
    }
    
    /**
     * Unzips the cassandra server
     * 
     * @throws ZipException 
     */
    public static void unzip() throws ZipException{
        String source = FileUtil.getAppName() + "cassandra.zip";
        String destination = __DESKTOPLOCATION__;
        String password = "password";

        ZipFile zipFile = new ZipFile(source);
        zipFile.extractAll(destination);
        FileUtil.makeLogVarDirs();
        FileUtil.writeToLog("[SUCCESS]\tExtracted cassandra without problems.");
    }
    
    
    /**
     * Setting up the correct permissions as ZIP files cannot keep file permissions.
     */
    public static void setExecPermissions() {
        
        java.util.List<File> executableFiles = Arrays.asList(
        new File(__CASSANDRASERVLOCATION__ + "bin/cassandra"),
        new File(__CASSANDRASERVLOCATION__ + "bin/cassandra-cli"),
        new File(__CASSANDRASERVLOCATION__ + "bin/cqlsh"),
        new File(__CASSANDRASERVLOCATION__ + "bin/debug-cql"),
        new File(__CASSANDRASERVLOCATION__ + "bin/json2sstable"),
        new File(__CASSANDRASERVLOCATION__ + "bin/nodetool"),
        new File(__CASSANDRASERVLOCATION__ + "bin/shuffle"),
        new File(__CASSANDRASERVLOCATION__ + "bin/sstable2json"),
        new File(__CASSANDRASERVLOCATION__ + "bin/sstablekeys"),
        new File(__CASSANDRASERVLOCATION__ + "bin/sstableloader"),
        new File(__CASSANDRASERVLOCATION__ + "bin/sstablescrub"),
        new File(__CASSANDRASERVLOCATION__ + "bin/stop-server"));
        
        for(File cassandraFile : executableFiles) {
            cassandraFile.setExecutable(true);
        }
    }
}
