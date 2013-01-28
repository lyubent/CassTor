package smail.cli;

import com.netflix.astyanax.Keyspace;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import smail.cli.astyanax.Astyanax;
import smail.cli.gui.LoginFrame;
import smail.cli.test.SchemaCreationTest;
import smail.cli.util.Crypto;


public class Main {    
    
    public static void main(String[] args) {
        
        //smail.cli.util.XMLReader.getData();
//        Keyspace ks = Astyanax.getKeyspaceContext();
        
        
        //test to try create indexed ks
        //System.out.println(Astyanax.createIndexedColumnFamily(ks));
        
        //test to insert some data into indexed ks
        //System.out.println(Astyanax.insertEmail(ks, "ltodorov", null));
        
        //test to read some of the previously inserted data
        //System.out.println();
        
        //test to drop column family
        //System.out.println(Astyanax.dropColumnFamily(ks, Astyanax.getColumnFamilyStructure()));

        
//        for(int i=0;i<50;i++){
//            System.out.println();
//        }
        
        
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                try {
                    LoginFrame loginFrame = new LoginFrame();
                    
                    SchemaCreationTest jj = new SchemaCreationTest();
                    jj.runTest();
                    
                } catch (Exception ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MailHomeForm().setVisible(true);
//            }
//        });
    }
    
    
    
    
    
    
}