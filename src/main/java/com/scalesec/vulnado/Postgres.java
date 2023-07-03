

package com.scalesec.vulnado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.UUID;

public class Postgres {

    public static Connection connection() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = new StringBuilder()
                    .append("jdbc:postgresql://")
                    .append(System.getenv("PGHOST"))
                    .append("/")
                    .append(System.getenv("PGDATABASE")).toString();
            return DriverManager.getConnection(url,
                    System.getenv("PGUSER"), System.getenv("PGPASSWORD"));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(1);
        }
        return null;
    }
    public static void setup(){
        try {
            System.out.println("Setting up Database...");
            Connection c = connection();
            Statement stmt = c.createStatement();

            // Create Schema
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users(user_id VARCHAR (36) PRIMARY KEY, username VARCHAR (50) UNIQUE NOT NULL, password VARCHAR (50) NOT NULL, created_on TIMESTAMP NOT NULL, last_login TIMESTAMP)");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS comments(id VARCHAR (36) PRIMARY KEY, username VARCHAR (36), body VARCHAR (500), created_on TIMESTAMP NOT NULL)");

            // Clean up any existing data
            stmt.executeUpdate("DELETE FROM users");
            stmt.executeUpdate("DELETE FROM comments");

            // Insert seed data
            insertUser("admin", "!!SuperSecretAdmin!!");
            insertUser("alice", "AlicePassword!");
            insertUser("bob", "BobPassword!");
            insertUser("eve", "$EVELknev^l");
            insertUser("rick", "!GetSchwifty!");

            insertComment("rick", "cool dog m8");
            insertComment("alice", "OMG so cute!");
            c.close();
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    public static String sha256(String input)
    {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static void insertUser(String username, String password) {
       String sql = "INSERT INTO users (user_id, username, password, created_on) VALUES (?, ?, ?, current_timestamp)";
       PreparedStatement pStatement = null;
       try {
          pStatement = connection().prepareStatement(sql);
          pStatement.setString(1, UUID.randomUUID().toString());
          pStatement.setString(2, username);
          pStatement.setString(3, sha256(password));
          pStatement.executeUpdate();
       } catch(Exception e) {
         e.printStackTrace();
       }
    }

    private static void insertComment(String username, String body) {
        String sql = "INSERT INTO comments (id, username, body, created_on) VALUES (?, ?, ?, current_timestamp)";
        PreparedStatement pStatement = null;
        try {
            pStatement = connection().prepareStatement(sql);
            pStatement.setString(1, UUID.randomUUID().toString());
            pStatement.setString(2, username);
            pStatement.setString(3, body);
            pStatement.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
