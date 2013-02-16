/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smail.cli.netlib;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.Closeables;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.silvertunnel.netlib.api.NetFactory;
import org.silvertunnel.netlib.api.NetLayer;
import org.silvertunnel.netlib.api.NetLayerIDs;
import org.silvertunnel.netlib.api.NetSocket;
import org.silvertunnel.netlib.api.util.TcpipNetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.silvertunnel.netlib.adapter.socket.ExtendedSocket;
import org.silvertunnel.netlib.adapter.socket.SocketGlobalUtil;
import org.silvertunnel.netlib.api.NetFactory;
import org.silvertunnel.netlib.api.NetLayer;
import org.silvertunnel.netlib.api.NetLayerIDs;
import org.silvertunnel.netlib.api.NetSocket;
import org.silvertunnel.netlib.api.util.JavaVersion;
import org.silvertunnel.netlib.util.ByteArrayUtil;
import org.silvertunnel.netlib.util.HttpUtil;
import org.silvertunnel.netlib.util.HttpUtilResponseReceiverThread;

/**
 *
 * @author lyubentodorov
 */
public class Anonymizer {
    
    private NetSocket netSocket;
    
    private static final Logger log = Logger.getLogger("");
    private static final long timeoutInMs = 5000;
    private static final String __SERVER__ = "github.com";
    
    public Anonymizer() {
        
    }
    
    public boolean tryConn() throws IOException {
        executeCheck(true);
        return true;
    }
    
    
    public static boolean executeCheck(boolean checkWithSocketGlobalUtil) {
//        log.info("CheckNetConnectivity.executeCheck()");
//        System.out.println("HttpUtil.HTTPTEST_SERVER_NAME");
    
        // execute test 1
       boolean test1Result = false;
        try {
            ExtendedSocket socket = new ExtendedSocket(__SERVER__, HttpUtil.HTTPTEST_SERVER_PORT);
//            ExtendedSocket socket = new ExtendedSocket("httptest.silvertunnel.org", HttpUtil.HTTPTEST_SERVER_PORT);
//            ExtendedSocket socket = new ExtendedSocket("178.77.97.221", HttpUtil.HTTPTEST_SERVER_PORT);
            test1Result = executeSmallTest(socket,timeoutInMs);
        } catch (Exception e) {
            log.log(Level.WARNING, "Exception while test1", e);
        }

        // execute test 2
//        boolean test2Result = false;
//        try {
//            NetSocket netSocket = NetFactory.getInstance().getNetLayerById(NetLayerIDs.TCPIP).
//                createNetSocket(null, null, HttpUtil.HTTPTEST_SERVER_NETADDRESS);
//            test2Result = HttpUtil.getInstance().executeSmallTest(netSocket, "test2",timeoutInMs);
//        } catch (Exception e) {
//            log.log(Level.WARNING, "Exception while test2", e);
//        }

        boolean test3Result;
        boolean test4Result;
        if (checkWithSocketGlobalUtil) {
//            // initialize and set SocketImplFactory
//            SocketGlobalUtil.initSocketImplFactory();
//            NetLayer netLayer = NetFactory.getInstance().getNetLayerById(NetLayerIDs.TCPIP);
//            SocketGlobalUtil.setNetLayerUsedBySocketImplFactory(netLayer);
//
//            // execute test 3
//            test3Result = false;
//            try {
//                ExtendedSocket socket = new ExtendedSocket(HttpUtil.HTTPTEST_SERVER_NAME, HttpUtil.HTTPTEST_SERVER_PORT);
//                test3Result = HttpUtil.getInstance().executeSmallTest(socket, "test3",timeoutInMs);
//            } catch (Exception e) {
//                log.log(Level.WARNING, "Exception while test3", e);
//            }

//            // execute test 4
//            test4Result = false;
//            try {
//                NetSocket netSocket = NetFactory.getInstance().getNetLayerById(NetLayerIDs.TCPIP).
//                    createNetSocket(null, null, HttpUtil.HTTPTEST_SERVER_NETADDRESS);
//                test4Result = HttpUtil.getInstance().executeSmallTest(netSocket, "test4",timeoutInMs);
//            } catch (Exception e) {
//                log.log(Level.WARNING, "Exception while test4", e);
//            }

        } else {
            test3Result = true;
            test4Result = true;
        }

        // show test results
//        print("===================================================");
//        print("=== Test Results ==================================");
//        print("===================================================");
//        print("JavaVersion: " + JavaVersion.getJavaVersion());
//        print("test1 (Socket    before initSocketImplFactory): "+(test1Result?"OK":"FAILED"));
//        print("test2 (NetSocket before initSocketImplFactory): "+(test2Result?"OK":"FAILED"));
//        if (checkWithSocketGlobalUtil) {
//            print("test3 (Socket    after initSocketImplFactory):  "+(test3Result?"OK":"FAILED"));
//            print("test4 (NetSocket after initSocketImplFactory):  "+(test4Result?"OK":"FAILED"));
//        }

        // result
//        return test1Result && test2Result && test3Result && test4Result;
        return false;
    }
    
    
    public static boolean executeSmallTest(
        NetSocket lowerLayerNetSocket, long timeoutInMs) throws IOException {
        // generate the id

        // communicate with the remote side
        byte[] httpResponse = HttpUtilTwo.getInstance().get(
                lowerLayerNetSocket,
                HttpUtilTwo.HTTPTEST_SERVER_NETADDRESS,
                "",
                timeoutInMs);

        // check response
        print("http response body: " + ByteArrayUtil.showAsString(httpResponse));
        
        //byte[] expectedResponse = ("<response><id>"+id+"</id></response>\n").getBytes("UTF-8");
        //boolean testOK = Arrays.equals(expectedResponse, httpResponse); 
//        if (testOK) {
//            print("http response body = expected response body");
//        } else {
//            print("expected http response body is different: "+ByteArrayUtil.showAsString(expectedResponse));
//        }

        lowerLayerNetSocket.close();

        return true;
    }
    

    private static final void print(String s) {
        System.out.println(s);
   }
    
    
    
    
    
    
    
    
    
    
    public boolean tryConnOLD() throws IOException {
        
        try {
            // define remote address
            String remoteHostname = "stackoverflow.com.";
            int remotePort = 80;
            TcpipNetAddress remoteAddress = new TcpipNetAddress(remoteHostname, remotePort);
//            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\nAaaa\n\n\n\n\n\n\n\n\n\n\n\n");
            // get TorNetLayer instance and wait until it is ready
            NetLayer netLayer = NetFactory.getInstance().getNetLayerById(NetLayerIDs.TCPIP);
//            NetLayer netLayer = NetFactory.getInstance().getNetLayerById(NetLayerIDs.TOR);
            netLayer.waitUntilReady();
            // open connection to remote address - this connection is tunneled through the TOR anonymity network
            netSocket = netLayer.createNetSocket(null, null, remoteAddress);
            // send and receive data
            // hint: to avoid dead locks: use separate threads for each direction
            //OutputStream os = netSocket.getOutputStream();
            InputStream is = netSocket.getInputStream();
            
//            if(os == null)
//                System.out.println("OS IS NULL");
            if(is == null)
                System.out.println("IS IS NULL");
            
            //System.out.println(os.toString());
            //ByteArrayOutputStream jj = (ByteArrayOutputStream) os;
            //System.out.println(new String(jj.toByteArray(), "UTF-8"));
            //            new String(os.toByteArray(), "");
            
//            String content = CharStreams.toString(new InputStreamReader(is, Charsets.UTF_8));
//            Closeables.closeQuietly(is);
            
            
            is.close();
            System.out.println(is.read());
            java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
             
            if(s.hasNext()) {
             System.out.println(s.next());   
             System.out.println();   
            }
            
            return true;
            
        } catch (IOException ex) {
            
        } finally {
//            netSocket.close();
        }
        
        return false;
    }
}
