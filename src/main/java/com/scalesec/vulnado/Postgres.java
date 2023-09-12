

package com.scalesec.vulnado;

import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.UUID;

public class Postgres {

    public static Connection connection() {
       ...
    }

    public static void setup(){
       ...
    }

    public static String getSecurePassword(String password) {
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(password, salt);
    }

    private static void insertUser(String username, String password) {
       String sql = "INSERT INTO users (user_id, username, password, created_on) VALUES (?, ?, ?, current_timestamp)";
       PreparedStatement pStatement = null;
       try {
           pStatement = connection().prepareStatement(sql);
           pStatement.setString(1, UUID.randomUUID().toString());
           pStatement.setString(2, username);
           pStatement.setString(3, getSecurePassword(password));
           pStatement.executeUpdate();
       } catch(Exception e) {
           e.printStackTrace();
       }
    }

    private static void insertComment(String username, String body) {
        ...
    }
}
