

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
        // Restante do código...
    }
    
    public static void setup() {
        if (!Boolean.parseBoolean(System.getenv("DEVELOPMENT_ENVIRONMENT"))) {
            return;
        }
        
        // Restante do código...
    }

    public static String md5(String input) {
        // Restante do código...
    }

    private static void insertUser(String username, String password) {
        // Restante do código...
    }

    private static void insertComment(String username, String body) {
        // Restante do código...
    }
}
