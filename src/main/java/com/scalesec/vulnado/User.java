package com.scalesec.vulnado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

public class User {
  private static final Logger LOGGER = Logger.getLogger(User.class.getName());
  private String id;
  private String username;
  private String hashedPassword;

  public User(String id, String username, String hashedPassword) {
    this.id = id;
    this.username = username;
    this.hashedPassword = hashedPassword;
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
      LOGGER.severe(e.getMessage());
      throw new Unauthorized(e.getMessage());
    }
  }

  public static User fetch(String un) {
    PreparedStatement stmt = null;
    User user = null;
    try {
      Connection cxn = Postgres.connection();
      stmt = cxn.prepareStatement("select * from users where username = ? limit 1");
      stmt.setString(1, un);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        String user_id = rs.getString("user_id");
        String username = rs.getString("username");
        String password = rs.getString("password");
        user = new User(user_id, username, password);
      }
      cxn.close();
    } catch (SQLException e) {
      LOGGER.severe(e.getClass().getName()+": "+e.getMessage());
    } finally {
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException e) {
          LOGGER.severe("Failed to close statement");
        }
      }
    }
    return user;
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
}