

package com.scalesec.vulnado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.UUID;

public class Postgres {
    public static Connection connection() {
        /* Same as before... */
    }

    public static void setup(){
        /* Same as before... */
    }

    // Java program to calculate SHA-256 hash value
    public static String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    private static void insertUser(String username, String password) {
        /* Replace md5 with sha256... */
        pStatement.setString(3, sha256(password));
        /* Rest of the method... */
    }

    private static void insertComment(String username, String body) {
        /* Same as before... */
    }
}
