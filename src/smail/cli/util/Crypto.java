package smail.cli.util;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

// @author lyubentodorov
// @licence - MIT
// Available at http://lyuben.herokuapp.com/casstor/ 
// Source at https://github.com/lyubent/CassTor/ 
//
public class Crypto {
  private static byte[] encrypt(byte[] inpBytes, PublicKey key,
      String xform) throws Exception {
    Cipher cipher = Cipher.getInstance(xform);
    cipher.init(Cipher.ENCRYPT_MODE, key);
    return cipher.doFinal(inpBytes);
  }
  private static byte[] decrypt(byte[] inpBytes, PrivateKey key,
      String xform) throws Exception{
    Cipher cipher = Cipher.getInstance(xform);
    cipher.init(Cipher.DECRYPT_MODE, key);
    return cipher.doFinal(inpBytes);
  }

    public static void test() throws Exception {
    String xform = "RSA/ECB/PKCS1Padding";
    // Generate a key-pair
    KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
    kpg.initialize(512); // 512 is the keysize.
    KeyPair kp = kpg.generateKeyPair();
    PublicKey pubk = kp.getPublic();
    PrivateKey prvk = kp.getPrivate();
    
    //System.out.println("" + pubk + "\n\n\n\n\n " + prvk);

    byte[] dataBytes =
        "J2EE Security for Servlets, EJBs and Web Services".getBytes();

    byte[] encBytes = encrypt(dataBytes, pubk, xform);
    
    byte[] decBytes = decrypt(encBytes, prvk, xform);
//    byte[] decBytes = decrypt(encByteZZ , prvk, xform);
    
    String source = new BigInteger(encBytes).toString();
    System.out.println(source);
    byte[] byteArray = source.getBytes("UTF-8");
//    byte[] decBytes = decrypt(byteArray, prvk, xform);

//    System.out.println("\n\n\n\n\n\n\n\n\n" + new BigInteger(encBytes));
    System.out.println("\n\n\n\n\n\n\n\n\n" + new String(decBytes));
    //boolean expected = java.util.Arrays.equals(dataBytes, decBytes);
    //System.out.println("Test " + (expected ? "SUCCEEDED!" : "FAILED!"));
  }
}