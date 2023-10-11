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
    return Jwts.builder().setSubject(this.username).signWith(key).compact();
  }

  public static void assertAuth(String secret, String token) {
    try {
      SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
      Jwts.parser().setSigningKey(key).parseClaimsJws(token);
    } catch(Exception e) {
      logger.severe(e.getMessage());
      throw new Unauthorized(e.getMessage());
    }
  }

  public static User fetch(String usernameInput) {
    User user = null;
    Connection cxn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      cxn = Postgres.connection();
      logger.info("Opened database successfully");

      String query = "select * from users where username = ? limit 1";
      pstmt = cxn.prepareStatement(query);
      pstmt.setString(1, usernameInput);

      rs = pstmt.executeQuery();
      if (rs.next()) {
        String userId = rs.getString("user_id");
        String username = rs.getString("username");
        String password = rs.getString("password");
        user = new User(userId, username, password);
      }
    } catch (Exception e) {
      logger.severe(e.getClass().getName()+": "+e.getMessage());
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
        if (pstmt != null) {
          pstmt.close();
        }
        if (cxn != null) {
          cxn.close();
        }
      } catch(Exception e) {
        logger.severe(e.getMessage());
      }
    }
    return user;
  }
}