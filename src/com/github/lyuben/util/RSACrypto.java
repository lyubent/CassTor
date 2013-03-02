package com.github.lyuben.util;

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
public class RSACrypto {
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

    byte[] dataBytes =
        "J2EE Security for Servlets, EJBs and Web Services".getBytes();

    byte[] encBytes = encrypt(dataBytes, pubk, xform);
    
    byte[] decBytes = decrypt(encBytes, prvk, xform);
    
    String source = new BigInteger(encBytes).toString();
    byte[] byteArray = source.getBytes("UTF-8");
  }
}