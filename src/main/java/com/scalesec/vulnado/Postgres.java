-----------------------

package com.scalesec.vulnado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.UUID;
import at.favre.lib.crypto.bcrypt.BCrypt;

public class Postgres {

    public static Connection connection() {
        // ... Restante do código ...

    }
    // ... outras funções ...

    // Função para aplicar o algoritmo bcrypt nas senhas
    public static String bcryptHash(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    private static void insertUser(String username, String password) {
       String sql = "INSERT INTO users (user_id, username, password, created_on) VALUES (?, ?, ?, current_timestamp)";
       PreparedStatement pStatement = null;
       try {
          pStatement = connection().prepareStatement(sql);
          pStatement.setString(1, UUID.randomUUID().toString());
          pStatement.setString(2, username);
          pStatement.setString(3, bcryptHash(password)); // Trocar MD5 por bcrypt
          pStatement.executeUpdate();
       } catch(Exception e) {
         e.printStackTrace();
       }
    }

    // ... outras funções ...
}
