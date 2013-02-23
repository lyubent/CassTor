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
    
    public FileUtil() {
    }
    
    
    
    // Reads text from a file and returns it as a string
    //
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
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, 
                    "Failed to get data from file, the file was not found.", ex);
        } catch (IOException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, 
                    "IOException occured while trying to get data from the file.", ex);
        }
        
        return "";
    }
    
    
    
    //
    //
    //
    public static String getTextFromFile(String fileURL, boolean onlyFirstLine) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileURL));
            return br.readLine();
            
        } catch (Exception ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, "Problem opening log file", ex);
        }
        
        return "";
    }
    
    
    
    // Opens the cassandra.yaml configuration file and updates the listen and
    // rpc address to the local address of the current machine allowing it to bootstrap
    // with the seed node and join the cluster ring.
    public static void configureCassandraYAML() {
        
        try {
            
            //Create a reader to read the file
            BufferedReader br = new BufferedReader(new FileReader(__CASSANDRACONFIGPATH__));
            java.util.List<String> file = new java.util.ArrayList();
            
            //Read the file in line by line and store it in a List of Strings
            String currentLine = "";
            while ((currentLine = br.readLine()) != null) {
               file.add(currentLine);
            }
            br.close();
            
            //Write the List of strings line by line back to the same file
            BufferedWriter fileOut = new BufferedWriter(new FileWriter(__CASSANDRACONFIGPATH__));
            for (String line : file) {
                //Replace the block with the machine's local IP address needed for joining the cassandra ring.
                line = line.replaceFirst("<INTERFACE_IP>", NetworkUtil.getInterfaceIP() + " # ADDED BY SCRIPT");
                fileOut.write(line + "\n");
            }
            
            fileOut.flush();
            fileOut.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, 
                    "Error configuring cassandra.YAML, couldn't find the file.", ex);
        } catch (IOException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, 
                    "Error configuring cassandra.YAML, IOException occured.", ex);
        }

    }
    
    
    
    // Writes log lines to the log file
    // log file found in root directory of this prog 
    //
    public static void writeToLog(String logDetail){
        try {
            PrintWriter out = new PrintWriter(
                    new BufferedWriter(new FileWriter("log", true)));
            
            out.println(logDetail + "\tLogged at:" + new java.util.Date().toString());
            out.flush();
            out.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, 
                    "Error writing to the logfile, failed to find the file.", ex);
        } catch (IOException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, 
                    "Error writing to the logfile, IOException occured.", ex);
        }
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
    
}
