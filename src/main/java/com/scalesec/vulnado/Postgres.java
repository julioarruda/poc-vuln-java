

package com.scalesec.vulnado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.UUID;
import java.sql.PreparedStatement;
import java.sql.Statement;
import org.mindrot.jbcrypt.BCrypt;

public class Postgres {

    public static Connection connection() {
        // ... código de conexão ... 
    }
    public static void setup(){
        // ... código de preparação ... 
    }

    public static String hashPassword(String password) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }

    private static void insertUser(String username, String password) {
       String sql = "INSERT INTO users (user_id, username, password, created_on) VALUES (?, ?, ?, current_timestamp)";
       PreparedStatement pStatement = null;
       try {
          pStatement = connection().prepareStatement(sql);
          pStatement.setString(1, UUID.randomUUID().toString());
          pStatement.setString(2, username);
          pStatement.setString(3, hashPassword(password));
          pStatement.executeUpdate();
       } catch(Exception e) {
         e.printStackTrace();
       }
    }

    private static void insertComment(String username, String body) {
        // ... código de inserção de comentário ... 
    }
}
