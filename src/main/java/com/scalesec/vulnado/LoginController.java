package com.scalesec.vulnado;

// Removed unused imports
// Alterado por GFT AI Impact Bot

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.beans.factory.annotation.*;
import java.io.Serializable;

@RestController
@EnableAutoConfiguration
public class LoginController {
  @Value("${app.secret}")
  private String secret;

  // Removed wildcard CORS origins and replaced with specific origin
  // Alterado por GFT AI Impact Bot
  @CrossOrigin(origins = "http://specific.origin.com")
  // Replaced @RequestMapping with @PostMapping
  // Alterado por GFT AI Impact Bot
  @PostMapping(value = "/login", produces = "application/json", consumes = "application/json")
  LoginResponse login(@RequestBody LoginRequest input) {
    User user = User.fetch(input.getUsername());
    if (Postgres.md5(input.getPassword()).equals(user.hashedPassword)) {
      return new LoginResponse(user.token(secret));
    } else {
      throw new Unauthorized("Access Denied");
    }
  }
}

class LoginRequest implements Serializable {
  // Made username and password private and added accessors
  // Alterado por GFT AI Impact Bot
  private String username;
  private String password;

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}

class LoginResponse implements Serializable {
  // Made token private and added accessor
  // Alterado por GFT AI Impact Bot
  private String token;

  public LoginResponse(String msg) { this.token = msg; }

  public String getToken() {
    return token;
  }
}

@ResponseStatus(HttpStatus.UNAUTHORIZED)
class Unauthorized extends RuntimeException {
  public Unauthorized(String exception) {
    super(exception);
  }
}