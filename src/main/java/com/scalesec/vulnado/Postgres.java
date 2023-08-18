

package com.scalesec.vulnado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.UUID;
import org.mindrot.jbcrypt.BCrypt;

public class Postgres {

    public static Connection connection() {
        // ...
    }
    
    public static void setup(){
        // ...
    }

    // New function to hash the password using bcrypt
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private static void insertUser(String username, String password) {
       // ...
       pStatement.setString(3, hashPassword(password));
       // ...
    }

    private static void insertComment(String username, String body) {
       // ...
    }
}
