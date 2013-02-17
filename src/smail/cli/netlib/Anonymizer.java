/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smail.cli.netlib;

import com.google.common.io.CharStreams;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.silvertunnel.netlib.adapter.socket.ExtendedSocket;
import org.silvertunnel.netlib.adapter.socket.SocketGlobalUtil;
import org.silvertunnel.netlib.api.NetFactory;
import org.silvertunnel.netlib.api.NetLayer;
import org.silvertunnel.netlib.api.NetLayerIDs;
import org.silvertunnel.netlib.api.NetSocket;
import org.silvertunnel.netlib.api.util.TcpipNetAddress;
import org.silvertunnel.netlib.util.ByteArrayUtil;
import org.silvertunnel.netlib.util.HttpUtil;

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
//
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
    
    
    public boolean tryPureConn() {
        try {
            
            String remoteHostname = "www.abv.bg";
            int remotePort = 80;
            TcpipNetAddress remoteAddress = new TcpipNetAddress(remoteHostname, remotePort);
            
            //NetLayer netLayer = NetFactory.getInstance().getNetLayerById(NetLayerIDs.TOR);
            NetLayer netLayer = NetFactory.getInstance().getNetLayerById(NetLayerIDs.TCPIP);
            netLayer.waitUntilReady();
            
            NetSocket netSocket = netLayer.createNetSocket(null, null, remoteAddress);
            Socket sss = (Socket)netSocket;
            
            
            OutputStream os = netSocket.getOutputStream();
            InputStream is = netSocket.getInputStream();
            
//            byte [] jj = 
//                    ByteArrayUtil.readDataFromInputStream(1024, is);
//            
//            System.out.println("bytes: " + jj.length);
            
            CharStreams.toString( new InputStreamReader( is ));
            
            return true;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        return false;
    }
    
    public boolean buildTORSocket() {
        try{
            // enable redirection (globally for the complete JVM)
            SocketGlobalUtil.initSocketImplFactory();

            // select the NetLayer implementation that should be used by java.net.Socket;
            //   default is TcpipNetLayer with NetLayerIDs.TCPIP;
            //   here we set TorNetLayer with NetLayerIDs.TOR:
            NetLayer netLayer = NetFactory.getInstance().getNetLayerById(NetLayerIDs.TOR); 

            //  wait (block the current thread) until this netLayer instance is up and ready
            netLayer.waitUntilReady();

            // redirect to the selected NetLayer
            SocketGlobalUtil.setNetLayerUsedBySocketImplFactory(netLayer);
            
            // create a socket
            Socket socket = new Socket();

            // connect - will be redirected to and tunneled by Tor anonymous network instead of direct TCP/IP connection
            InetSocketAddress address = new InetSocketAddress(/*hostname*/"silvertunnel.org", /*port*/80);
            socket.connect(address);
            
            return true;
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
}
