package smail.cli.netlib;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import org.silvertunnel.netlib.adapter.java.JvmGlobalUtil;
import org.silvertunnel.netlib.api.NetFactory;
import org.silvertunnel.netlib.api.NetLayer;
import org.silvertunnel.netlib.api.NetLayerIDs;
import org.silvertunnel.netlib.api.NetServerSocket;
import org.silvertunnel.netlib.api.NetSocket;
import org.silvertunnel.netlib.api.util.TcpipNetAddress;

public class TorSocket extends Thread {
    //private NetServerSocket objectServer;
    private NetSocket objectServer;
    
    public static void main(String ... s) throws Exception{
        setDefaultLayer();
        
        TorSocket ts = new TorSocket(3000);
        
        //Socket ss = ts.objectServer;
        
        ts.start();
    }
     
    public static void main(String s2, boolean j) {
        try
        {   
            ServerSocket server=new ServerSocket(9160);  
            Socket s=server.accept();  
            System.out.println("Client Accepted");  
            BufferedReader br=new BufferedReader(new  
            InputStreamReader(s.getInputStream()));  
            System.out.println(br.readLine());  
            PrintWriter wr=new PrintWriter(new  
            OutputStreamWriter(s.getOutputStream()),true);  
            wr.println("Welcome to Socket Programming");
        } catch(Exception e){
            System.out.println(e);
        }  
    }
    
    public TorSocket(int port) throws Exception {
        TcpipNetAddress localListenAddress = new TcpipNetAddress("0.0.0.0", 3000);
        objectServer = NetFactory.getInstance()
                .getNetLayerById(NetLayerIDs.TCPIP)
                .createNetSocket(null, localListenAddress, localListenAddress);
                //.createNetServerSocket(null, localListenAddress);

    } 

     public static void setDefaultLayer() {
            JvmGlobalUtil.init();
            NetLayer torNetLayer = NetFactory.getInstance().getNetLayerById(NetLayerIDs.TOR);
            torNetLayer.waitUntilReady();
            JvmGlobalUtil.setNetLayerAndNetAddressNameService(torNetLayer, true);
     }

       
       
//       public void run() {
//           while(true) {
//               try {
//                   System.out.println("Waiting for connections.");
//                   NetSocket client = objectServer.accept();
//                   System.out.println("Accepted a connection from: "+client);
//                   Connect c = new Connect(client);
//                   c.start();
//               } catch(Exception e) {}
//           }
//       }
       
//       class Connect extends Thread {
//           private NetSocket client = null;
//           public Connect(NetSocket clientSocket) {
//               client = clientSocket;
//               try {
//                   ObjectInputStream ois = new ObjectInputStream(client.getInputStream()); //HERE THE INPUTSTREAM IS NULL...
//                   ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
//               } catch(Exception e1) {
//                   e1.printStackTrace();
//                 return;
//             }
//             this.start();
//           }
//          
//           public void run() {
//           }
//        }      
}