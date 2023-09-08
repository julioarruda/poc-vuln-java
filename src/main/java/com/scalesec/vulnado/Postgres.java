-----------------


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
        try {
            ...
        } catch (Exception e) {
            ...
        }
        return null;
    }
    
    public static void setup(){
        String isProd = System.getenv("IS_PRODUCTION");

        try {
            System.out.println("Setting up Database...");
            Connection c = connection();

            if(isProd == null || !isProd.equals("true")) {
              createSchema(c);
              seedDatabase(c);
            }
            
            c.close();
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    private static void createSchema(Connection c) throws Exception {
        Statement stmt = c.createStatement();

        // Create Schema
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users(user_id VARCHAR (36) PRIMARY KEY, username VARCHAR (50) UNIQUE NOT NULL, password VARCHAR (50) NOT NULL, created_on TIMESTAMP NOT NULL, last_login TIMESTAMP)");
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS comments(id VARCHAR (36) PRIMARY KEY, username VARCHAR (36), body VARCHAR (500), created_on TIMESTAMP NOT NULL)");

        // Clean up any existing data
        stmt.executeUpdate("DELETE FROM users");
        stmt.executeUpdate("DELETE FROM comments");
    }

    private static void seedDatabase(Connection c) throws Exception {
        // Insert seed data
        insertUser("admin", "!!SuperSecretAdmin!!");
        insertUser("alice", "AlicePassword!");
        insertUser("bob", "BobPassword!");
        insertUser("eve", "$EVELknev^l");
        insertUser("rick", "!GetSchwifty!");

        insertComment("rick", "cool dog m8");
        insertComment("alice", "OMG so cute!");
    }

    ...

    private static void insertUser(String username, String password) {
       ...
    }

    private static void insertComment(String username, String body) {
        ...
    }
}
