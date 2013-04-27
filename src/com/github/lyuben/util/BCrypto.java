package com.github.lyuben.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * @author lyubentodorov
 * @licence - MIT
 * Available at http://lyuben.herokuapp.com/casstor/ 
 * Source at https://github.com/lyubent/CassTor/ 
 */
public abstract class BCrypto {
    
    public static String generateHast(String password) {
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        
        System.out.println(hashed);
        if (BCrypt.checkpw("password", hashed))
            System.out.println("It matches");
        
        return hashed;
    }
    
    public static boolean checkPassword(String password, String hashedPw) {
        String hashed = ""; //get hashed from db
        
        return BCrypt.checkpw(password, hashedPw);
    }
}
