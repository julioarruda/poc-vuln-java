package com.scalesec.vulnado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger; //Modified by GFT AI Impact Bot
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

public class User {
  private static final Logger LOGGER = Logger.getLogger(User.class.getName()); //Added by GFT AI Impact Bot
  private String id; //Modified by GFT AI Impact Bot
  private String username; //Modified by GFT AI Impact Bot
  private String hashedPassword; //Modified by GFT AI Impact Bot

  public User(String id, String username, String hashedPassword) {
    this.id = id;
    this.username = username;
    this.hashedPassword = hashedPassword;
  }

  public String getId() {
    return id; //Added by GFT AI Impact Bot
  }

  public String getUsername() {
    return username; //Added by GFT AI Impact Bot
  }

  public String getHashedPassword() {
    return hashedPassword; //Added by GFT AI Impact Bot
  }

  public String token(String secret) {
    SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
    return Jwts.builder().setSubject(this.username).signWith(key).compact(); //Modified by GFT AI Impact Bot
  }

  public static void assertAuth(String secret, String token) {
    try {
      SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
      Jwts.parser().setSigningKey(key).parseClaimsJws(token);
    } catch(Exception e) {
      LOGGER.severe(e.getMessage()); //Modified by GFT AI Impact Bot
      throw new Unauthorized(e.getMessage());
    }
  }

  public static User fetch(String un) {
    User user = null;
    try (Connection cxn = Postgres.connection(); //Modified by GFT AI Impact Bot
         PreparedStatement stmt = cxn.prepareStatement("select * from users where username = ? limit 1")) { //Modified by GFT AI Impact Bot
      LOGGER.info("Opened database successfully"); //Modified by GFT AI Impact Bot

      stmt.setString(1, un); //Added by GFT AI Impact Bot
      LOGGER.info(stmt.toString()); //Modified by GFT AI Impact Bot
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        String user_id = rs.getString("user_id");
        String username = rs.getString("username");
        String password = rs.getString("password");
        user = new User(user_id, username, password);
      }
    } catch (Exception e) {
      LOGGER.severe(e.getClass().getName()+": "+e.getMessage()); //Modified by GFT AI Impact Bot
    }
    return user; //Modified by GFT AI Impact Bot
  }
}