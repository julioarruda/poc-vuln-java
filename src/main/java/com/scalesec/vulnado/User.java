package com.scalesec.vulnado;

import java.sql.Connection;
import java.sql.PreparedStatement; //Changed from Statement to PreparedStatement
import java.sql.ResultSet;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.logging.Logger; //Added logging import
import java.sql.SQLException; //Added SQLException import

public class User {
  private String id; // Made non-public
  private String username; // Made non-public
  private String hashedPassword; // Made non-public

  private static final Logger LOGGER = Logger.getLogger(User.class.getName()); // Added logger

  // Added accessors
  public String getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getHashedPassword() {
    return hashedPassword;
  }

  public User(String id, String username, String hashedPassword) {
    this.id = id;
    this.username = username;
    this.hashedPassword = hashedPassword;
  }

  public String token(String secret) {
    SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
    return Jwts.builder().setSubject(this.username).signWith(key).compact(); //Immediately return this expression instead of assigning it to the temporary variable "jws".
  }

  public static void assertAuth(String secret, String token) {
    try {
      SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
      Jwts.parser()
        .setSigningKey(key)
        .parseClaimsJws(token);
    } catch(Exception e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e); //Replaced e.printStackTrace() with logger
      throw new Unauthorized(e.getMessage());
    }
  }

  public static User fetch(String username) { //Renamed "un" to "username"
    User user = null;
    try (Connection cxn = Postgres.connection();
         PreparedStatement stmt = cxn.prepareStatement("select * from users where username = ? limit 1")) { //Used PreparedStatement instead of Statement to prevent SQL Injection
      stmt.setString(1, username); //Set username in PreparedStatement
      LOGGER.log(Level.INFO, "Opened database successfully"); //Replaced System.out.println with logger

      try (ResultSet rs = stmt.executeQuery()) { //Used try-with-resources to automatically close ResultSet
        if (rs.next()) {
          String userId = rs.getString("user_id"); //Renamed "user_id" to "userId" to match the regular expression '^[a-z][a-zA-Z0-9]*$'
          String username = rs.getString("username"); 
          String password = rs.getString("password");
          user = new User(userId, username, password);
        }
      }
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e); //Replaced System.err.println with logger
    }
    return user; //Moved return statement out of finally block
  }
}