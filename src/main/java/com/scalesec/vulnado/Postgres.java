

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
        /* O restante do código é o mesmo, apenas adicione o método main no final da classe */
        
    public static void main(String[] args) {
        if (System.getenv("APP_ENV") == null || !System.getenv("APP_ENV").equalsIgnoreCase("production")) {
            setup();
        }
    }
}
