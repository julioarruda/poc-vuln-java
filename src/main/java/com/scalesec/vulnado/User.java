package com.scalesec.vulnado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

public class User {
  private static final Logger logger = Logger.getLogger(User.class.getName()); // CVE Alterado por GFT AI Impact Bot
  private String id, username, hashedPassword; // CVE Alterado por GFT AI Impact Bot

  public User(String id, String username, String hashedPassword) {
    this.id = id;
    this.username = username;
    this.hashedPassword = hashedPassword;
  }

  public String getId() { // CVE Alterado por GFT AI Impact Bot
    return id;
  }

  public String getUsername() { // CVE Alterado por GFT AI Impact Bot
    return username;
  }

  public String getHashedPassword() { // CVE Alterado por GFT AI Impact Bot
    return hashedPassword;
  }

  public String token(String secret) {
    SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
    return Jwts.builder().setSubject(this.username).signWith(key).compact(); // CVE Alterado por GFT AI Impact Bot
  }

  public static void assertAuth(String secret, String token) {
    try {
      SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
      Jwts.parser()
        .setSigningKey(key)
        .parseClaimsJws(token);
    } catch(Exception e) {
      logger.severe(e.getMessage()); // CVE Alterado por GFT AI Impact Bot
      throw new Unauthorized(e.getMessage());
    }
  }

  public static User fetch(String un) {
    PreparedStatement stmt = null; // CVE Alterado por GFT AI Impact Bot
    User user = null;
    try (Connection cxn = Postgres.connection()) { // CVE Alterado por GFT AI Impact Bot
      stmt = cxn.prepareStatement("select * from users where username = ? limit 1"); // CVE Alterado por GFT AI Impact Bot
      stmt.setString(1, un); // CVE Alterado por GFT AI Impact Bot
      logger.info("Opened database successfully"); // CVE Alterado por GFT AI Impact Bot

      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        String userId = rs.getString("user_id"); // CVE Alterado por GFT AI Impact Bot
        String username = rs.getString("username");
        String password = rs.getString("password");
        user = new User(userId, username, password);
      }
    } catch (Exception e) {
      logger.severe(e.getClass().getName()+": "+e.getMessage()); // CVE Alterado por GFT AI Impact Bot
    }
    return user; // CVE Alterado por GFT AI Impact Bot
  }
}