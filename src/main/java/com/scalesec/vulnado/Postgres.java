

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

    public static final boolean IN_DEV = "dev".equalsIgnoreCase(System.getenv("APP_ENV"));

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

    public static void main(String[] args) {
        if (IN_DEV) {
            setup(); // Apenas chamar setup() se estiver em desenvolvimento
        }
    }

    public static void setup(){
        // Restante do código setup() sem alterações
    }

    public static String md5(String input)
    {
        // Restante do código md5(String input) sem alterações
    }

    private static void insertUser(String username, String password) {
       // Restante do código insertUser(String username, String password) sem alterações
    }

    private static void insertComment(String username, String body) {
        // Restante do código insertComment(String username, String body) sem alterações
    }
}
