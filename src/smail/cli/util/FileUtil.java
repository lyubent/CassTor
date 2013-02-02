package smail.cli.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
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
              Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
          } catch (SAXException ex){
              Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
          } catch (IOException ex){
              Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    
}
