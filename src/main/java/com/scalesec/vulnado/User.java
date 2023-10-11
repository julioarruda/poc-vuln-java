package com.scalesec.vulnado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.crypto.SecretKey;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class User {
  private static final String id = null;
  private static final String username = null;
  private static final String hashedPassword = null;

  public User(String id, String username, String hashedPassword) {
    //TBD
  }

  public String token(String secret) {
    SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
    return Jwts.builder().setSubject(User.username).signWith(key).compact();
  }

  public static void assertAuth(String secret, String token) {
    try {
      SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
      Jwts.parser()
        .setSigningKey(key)
        .parseClaimsJws(token);
    } catch(Exception e) {
      e.printStackTrace();
      throw new Unauthorized(e.getMessage());
    }
  }

  public static User fetch(String un) {
    PreparedStatement ps = null;
    User user = null;
    try {
      Connection cxn = Postgres.connection();
      String query = "select * from users where username = ? limit 1";
      ps = cxn.prepareStatement(query);
      ps.setString(1, un);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        String userId = rs.getString("user_id");
        String userUsername = rs.getString("username");
        String password = rs.getString("password");
        user = new User(userId, userUsername, password);
      }
      cxn.close();
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName()+": "+e.getMessage());
      throw new RuntimeException(e);
    } finally {
      try {
        if(ps != null) {
          ps.close();
        }
      } catch(Exception e) {
        throw new RuntimeException(e);
      }
    }
    return user;
  }
}