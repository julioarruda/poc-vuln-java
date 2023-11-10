package com.scalesec.vulnado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

public class User {
  private static final Logger logger = Logger.getLogger(User.class.getName());

  private String id;
  private String username;
  private String hashedPassword;

  public User(String id, String username, String hashedPassword) {
    this.id = id;
    this.username = username;
    this.hashedPassword = hashedPassword;
  }

  public String getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getHashedPassword() {
    return hashedPassword;
  }

  public String token(String secret) {
    SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
    return Jwts.builder().setSubject(this.username).signWith(key).compact(); // Vulnerability ID: Immediately return this expression instead of assigning it to the temporary variable "jws".
  }

  public static void assertAuth(String secret, String token) {
    try {
      SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
      Jwts.parser()
        .setSigningKey(key)
        .parseClaimsJws(token);
    } catch(Exception e) {
      logger.severe(e.getMessage());
      throw new Unauthorized(e.getMessage());
    }
  }

  public static User fetch(String un) {
    User user = null;
    try (Connection cxn = Postgres.connection();
      PreparedStatement stmt = cxn.prepareStatement("select * from users where username = ? limit 1")) { // Vulnerability ID: Change this code to not construct SQL queries directly from user-controlled data.
      
      stmt.setString(1, un);

      logger.info("Opened database successfully");

      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        String userId = rs.getString("user_id"); // Vulnerability ID: Rename this local variable to match the regular expression '^[a-z][a-zA-Z0-9]*$'.
        String username = rs.getString("username");
        String password = rs.getString("password");
        user = new User(userId, username, password);
      }
    } catch (Exception e) {
      logger.severe(e.getClass().getName()+": "+e.getMessage());
    }
    return user; // Vulnerability ID: Remove this return statement from this finally block.
  }
}