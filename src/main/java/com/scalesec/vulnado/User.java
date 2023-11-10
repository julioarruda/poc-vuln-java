package com.scalesec.vulnado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

public class User {
  private static final Logger LOGGER = Logger.getLogger(User.class.getName()); // Corrected by GFT AI Impact Bot
  private String id; // Corrected by GFT AI Impact Bot
  private String username; // Corrected by GFT AI Impact Bot
  private String hashedPassword; // Corrected by GFT AI Impact Bot

  public User(String id, String username, String hashedPassword) {
    this.id = id;
    this.username = username;
    this.hashedPassword = hashedPassword;
  }

  public String token(String secret) {
    SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
    return Jwts.builder().setSubject(this.username).signWith(key).compact(); // Corrected by GFT AI Impact Bot
  }

  public static void assertAuth(String secret, String token) {
    try {
      SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
      Jwts.parser()
        .setSigningKey(key)
        .parseClaimsJws(token);
    } catch(Exception e) {
      LOGGER.severe(e.getMessage()); // Corrected by GFT AI Impact Bot
      throw new Unauthorized(e.getMessage());
    }
  }

  public static User fetch(String un) {
    PreparedStatement stmt = null; // Corrected by GFT AI Impact Bot
    User user = null;
    try {
      Connection cxn = Postgres.connection();
      LOGGER.info("Opened database successfully"); // Corrected by GFT AI Impact Bot

      String query = "select * from users where username = ? limit 1"; // Corrected by GFT AI Impact Bot
      stmt = cxn.prepareStatement(query); // Corrected by GFT AI Impact Bot
      stmt.setString(1, un); // Corrected by GFT AI Impact Bot
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        String userId = rs.getString("user_id"); // Corrected by GFT AI Impact Bot
        String username = rs.getString("username");
        String password = rs.getString("password");
        user = new User(userId, username, password);
      }
      cxn.close();
    } catch (Exception e) {
      LOGGER.severe(e.getClass().getName()+": "+e.getMessage()); // Corrected by GFT AI Impact Bot
    } finally {
      try {
        if (stmt != null) {stmt.close();} // Corrected by GFT AI Impact Bot
      } catch (Exception e) {
        LOGGER.severe("Failed to close statement: " + e.getMessage()); // Corrected by GFT AI Impact Bot
      }
      return user;
    }
  }

  // Accessor methods // Corrected by GFT AI Impact Bot
  public String getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getHashedPassword() {
    return hashedPassword;
  }
}