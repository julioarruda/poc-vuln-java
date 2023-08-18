

package com.scalesec.vulnado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.UUID;
import org.mindrot.jbcrypt.BCrypt; // Adicione essa importação

public class Postgres {
    // O restante do código permanece o mesmo

    // Substitua o método md5 pelo seguinte:
    public static String hashPassword(String input) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(input, salt);
    }

    private static void insertUser(String username, String password) {
       String sql = "INSERT INTO users (user_id, username, password, created_on) VALUES (?, ?, ?, current_timestamp)";
       PreparedStatement pStatement = null;
       try {
          pStatement = connection().prepareStatement(sql);
          pStatement.setString(1, UUID.randomUUID().toString());
          pStatement.setString(2, username);
          pStatement.setString(3, hashPassword(password)); // Modifique esta linha
          pStatement.executeUpdate();
       } catch(Exception e) {
         e.printStackTrace();
       }
    }

    // O restante do código permanece o mesmo
}
