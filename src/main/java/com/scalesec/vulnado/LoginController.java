package com.scalesec.vulnado;

import org.springframework.boot.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import java.io.Serializable;

@RestController
@EnableAutoConfiguration
public class LoginController {
  @Value("${app.secret}")
  private String secret;

  @CrossOrigin(origins = "*")
  @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
  public LoginResponse login(@RequestBody LoginRequest input) {
    User user = User.fetch(input.username);
    if (user != null && Postgres.md5(input.password).equals(user.hashedPassword)) {
      return new LoginResponse(user.token(secret));
    } else {
      throw new Unauthorized("Access Denied");
    }
  }
}

class LoginRequest implements Serializable {
  public String username;
  public String password;
}

class LoginResponse implements Serializable {
  public String token;
  public LoginResponse(String msg) { this.token = msg; }
}

@ResponseStatus(HttpStatus.UNAUTHORIZED)
class Unauthorized extends RuntimeException {
  public Unauthorized(String exception) {
    super(exception);
  }
}

class User {
  public String username;
  public String hashedPassword;
  public String token(String secret) {
    // Returning a dummy token for demonstration purposes, replace this with your actual implementation
    return "dummy-token";
  }
  
  public static User fetch(String username) {
    // Replace this with your actual implementation to fetch user from database or other data source
    return null;
  }
}

class Postgres {
  public static String md5(String text) {
    // Replace this with your actual implementation of MD5 hashing
    return text;
  }
}