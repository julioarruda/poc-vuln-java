

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
    // ...
    public static Connection connection() {
        try {
            // ...
        } catch (Exception e) {
            System.err.println("Erro na conexão com o banco de dados");
            System.exit(1);
        }
        return null;
    }

    public static void setup(){
        try {
            // Evitar a limpeza de dados valiosos.
            Connection c = connection();
            Statement stmt = c.createStatement();
            // ...
        } catch (Exception e) {
            System.out.println("Erro na configuração do banco de dados");
            System.exit(1);
        }
    }
    // ...
    private static void insertUser(String username, String password) {
       // ...
       try {
          // ...
       } catch(Exception e) {
         System.out.println("Erro na inserção do usuário");
       }
    }

    private static void insertComment(String username, String body) {
        // ...
        try {
            // ...
        } catch(Exception e) {
            System.out.println("Erro na inserção do comentário");
        }
    }
}
