package com.github.lyuben.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

// @author lyubentodorov
// @licence - MIT
// Available at http://lyuben.herokuapp.com/casstor/ 
// Source at https://github.com/lyubent/CassTor/ 
//
public abstract class Base64Crypto {
    
    // Decodes the base64 strings x2 as they were encoded twice.
    // @return String representing the plain text that was originally encoded.
    public static String decode(String cypher) {
        return StringUtils.newStringUtf8(Base64.decodeBase64(
                StringUtils.newStringUtf8(Base64.decodeBase64(cypher))
               ));
    }
    
    // Encodes a plain string into double BASE64
    // Reasoning behind double encoding
    // It removes the '==' at the end of the string obfuscating the encoding.
    // @return A doubly-base64 encoded string.
    public static String encode(String plain) {
        
        return Base64.encodeBase64String(StringUtils.getBytesUtf8(
                Base64.encodeBase64String(StringUtils.getBytesUtf8(plain))
               ));
    }
}
