package com.github.lyuben.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// @author lyubentodorov
// @licence - MIT
// Available at http://lyuben.herokuapp.com/casstor/ 
// Source at https://github.com/lyubent/CassTor/ 
//
public class FileUtil {
    
    private static final String __CASSANDRACONFIGPATH__ = 
        System.getProperty("user.home") + "/Desktop/cassandra/conf/cassandra.yaml";
    private static final String __CASSANDRABINPATH__ = 
        System.getProperty("user.home") + "/Desktop/cassandra/bin/cassandra";
    
    // Reads text from a file and returns it as a string
    // Iterates through the file line-by-line.
    //
    public static String getTextFromFile(String fileURL) {
        
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileURL));
            String currentLine = "";
            String fileData = "";
            
            while ((currentLine = br.readLine()) != null) {
               fileData += "\n" + currentLine;
            }
            br.close();

            return fileData;
        } catch (Exception ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, 
                    "Failed to getTextFromFile.", ex);
        }
        
        return "";
    }
    
    
    
    //
    public static void startCassandraServer() throws IOException {
        
        if(System.getProperty("os.name").toLowerCase().contains("win")) {
            // Windows.
        }
        
        //Unix systems.
        Process p = Runtime.getRuntime().exec(__CASSANDRABINPATH__);
    }
    
    
    
    // Opens a text file and returns the first line of text.
    //
    public static String getTextFromFile(String fileURL, boolean onlyFirstLine) 
            throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileURL));
        return br.readLine();
    }
    
    
    
    // Opens the cassandra.yaml configuration file and updates the listen and
    // rpc address to the local address of the current machine allowing it to bootstrap
    // with the seed node and join the cluster ring.
    public static void configureCassandraYAML() throws FileNotFoundException, IOException {
        // Create a reader to read the file
        BufferedReader br = new BufferedReader(new FileReader(__CASSANDRACONFIGPATH__));
        java.util.List<String> file = new java.util.ArrayList();

        // Read the file in line by line and store it in a List of Strings
        String currentLine = "";
        while ((currentLine = br.readLine()) != null) {
           file.add(currentLine);
        }
        br.close();

        // Write the List of strings line by line back to the same file
        BufferedWriter fileOut = new BufferedWriter(new FileWriter(__CASSANDRACONFIGPATH__));
        for (String line : file) {
            //Replace the block with the machine's local IP address needed for joining the cassandra ring.
            line = line.replaceFirst("<INTERFACE_IP>", NetworkUtil.getInterfaceIP());
            line = line.replaceFirst("<LIB_LOCATION>", System.getProperty("user.home") + "/Desktop/cassandra/" + "var/lib");
            fileOut.write(line + "\n");
        }
        // Clean and close the stream.
        fileOut.flush();
        fileOut.close();

    }
    
    
    
    // Writes log lines to the log file
    // log file found in root directory of this prog 
    //
    public static void writeToLog (String logDetail) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(
                getAppName() + "log", true)));
            out.println(logDetail + "\tLogged at:" + new java.util.Date().toString());
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }
    
    
    
    // Checks if log file contains data 
    // Determines if program has been previously run.
    //
    public static boolean isFirstRun() throws FileNotFoundException, IOException {
        System.out.println(System.getProperty("user.dir"));
        return FileUtil.getTextFromFile(getAppName() + "log", true) == null;
    }
    
    
    
    // OS Dependant method. 
    // Targeted at OSX users
    // Typical URL "/Users/lyubentodorov/Desktop/Dev/CassTor_v3/CassTor.app/Contents/Resources/Java/LICENCE.md"
    public static String getAppName() {
        if(System.getProperty("os.name").toLowerCase().contains("mac")) {
            // Find the full path of resources, required to work out the App name.
            String resourecePath = "".getClass().getResource("/com/github/lyuben/img/title.png")
              .getPath().replaceAll("!", "").replaceFirst("file:","");
            //Split by directories
            String[] dirArray = resourecePath.split("/");

            // Loop through each directory and check names
            for(String currentDir : dirArray){
                // If its  an app return it's name + the OSX app structure tree
                if(currentDir.contains(".app")){
                    return  System.getProperty("user.dir") + "/" +
                            currentDir + "/Contents/Resources/Java/";
                }
            }
        }
        // Not OSX or JAR is not bundled as app.
        return System.getProperty("user.dir") + "/";
    }

    
    
    // Used to find all the tags inside and XML file
    // Currently not used. Was part of getXMLData() function.
    //
    @Deprecated
    private static String getTagValue(String sTag, Element eElement) {
	NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
	return nValue.getNodeValue();
    }
    
    
    
    // Gets data from an XML file and displayes it
    // Currently not used. Was part of key exchange systet but is scrapped 
    // due to security issues.
    @Deprecated
    public static void getXMLData() {
 
	  try {
 
		File fXmlFile = new File("file.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
 
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		NodeList nList = doc.getElementsByTagName("detail");
		System.out.println("-----------------------");
 
		for (int temp = 0; temp < nList.getLength(); temp++) {
 
		   Node nNode = nList.item(temp);
		   if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
		      Element eElement = (Element) nNode;
 
		      System.out.println("User : " + getTagValue("username", eElement));
		      System.out.println("Key : " + getTagValue("key", eElement));
 
		   }
		}
	  } catch (ParserConfigurationException ex){
              Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, 
                      "ParserConfigurationException when trying to read in XML data", ex);
          } catch (SAXException ex){
              Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, 
                      "SAXException when trying to read in XML data", ex);
          } catch (IOException ex){
              Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, 
                      "IOException when trying to read in XML data", ex);
          }
    }
    
    
    
    // Creates a location to store the cassandra logs and data
    // Created locally to allow for non root execution
    //
    public static void makeLogVarDirs() {
        new File(System.getProperty("user.home") + "/Desktop/cassandra/var").mkdir();
        new File(System.getProperty("user.home") + "/Desktop/cassandra/var/lib").mkdir();
        new File(System.getProperty("user.home") + "/Desktop/cassandra/var/lib/cassandra").mkdir();
        new File(System.getProperty("user.home") + "/Desktop/cassandra/var/lib/cassandra/commitlog").mkdir();
        new File(System.getProperty("user.home") + "/Desktop/cassandra/var/lib/cassandra/data").mkdir();
        new File(System.getProperty("user.home") + "/Desktop/cassandra/var/lib/cassandra/saved_caches").mkdir();
    }
    
}
