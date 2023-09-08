

package com.scalesec.vulnado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.UUID;

public class Postgres {

    //...Other methods stay the same

    public static String getEncryptedPassword(String password)
    {
        try {

            //Generate Salt
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            byte[] salt = new byte[16];
            sr.nextBytes(salt);

            // Create PBEKeySpec
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);

            // Get SecretKeyFactory
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            // Hash the password
            byte[] hash = factory.generateSecret(spec).getEncoded();

            // Returning the Hashed password
            return new BigInteger(1, hash).toString(16);

        } catch (Exception e) {
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
            pStatement.setString(3, getEncryptedPassword(password));
            pStatement.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    //...Other methods stay the same

}

