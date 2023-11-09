package com.scalesec.vulnado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

public class User {
  private static final String ID = "id";
  private static final String USERNAME = "username";
  private static final String HASHED_PASSWORD = "hashedPassword";
  
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
      Jwts.parser()
        .setSigningKey(key)
        .parseClaimsJws(token);
    } catch(Exception e) {
      Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
      throw new Unauthorized(e.getMessage());
    }
  }

  public static User fetch(String un) {
    PreparedStatement stmt = null;
    User user = null;
    try {
      Connection cxn = Postgres.connection();
      Logger.getLogger(User.class.getName()).log(Level.INFO, "Opened database successfully");

      String query = "select * from users where username = ? limit 1";
      stmt = cxn.prepareStatement(query);
      stmt.setString(1, un);
      ResultSet rs = stmt.executeQuery();
      
      if (rs.next()) {
        String user_id = rs.getString(ID);
        String username = rs.getString(USERNAME);
        String password = rs.getString(HASHED_PASSWORD);
        user = new User(user_id, username, password);
      }
    } catch (Exception e) {
      Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
    } finally {
      if (stmt != null) {
        try {
          stmt.close();
        } catch (Exception e) {
          Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
        }
      }
      return user;
    }
  }
}