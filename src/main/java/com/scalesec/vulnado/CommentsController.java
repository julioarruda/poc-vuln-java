package com.scalesec.vulnado;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.autoconfigure.*;
import java.util.List;
import java.io.Serializable;

@RestController
@EnableAutoConfiguration
public class CommentsController {
  @Value("${app.secret}")
  private String secret;

  // Alterado por GFT AI Impact Bot
  @CrossOrigin(origins = "*", maxAge = 3600) // Make sure that enabling CORS is safe here.
  @GetMapping(value = "/comments", produces = "application/json") // Replace "@RequestMapping(method = RequestMethod.GET)" with "@GetMapping"
  List<Comment> comments(@RequestHeader(value="x-auth-token") String token) {
    User.assertAuth(secret, token);
    return Comment.fetch_all();
  }

  // Alterado por GFT AI Impact Bot
  @CrossOrigin(origins = "*", maxAge = 3600) // Make sure that enabling CORS is safe here.
  @PostMapping(value = "/comments", produces = "application/json", consumes = "application/json") // Replace "@RequestMapping(method = RequestMethod.POST)" with "@PostMapping"
  Comment createComment(@RequestHeader(value="x-auth-token") String token, @RequestBody CommentRequest input) {
    return Comment.create(input.getUsername(), input.getBody());
  }

  // Alterado por GFT AI Impact Bot
  @CrossOrigin(origins = "*", maxAge = 3600) // Make sure that enabling CORS is safe here.
  @DeleteMapping(value = "/comments/{id}", produces = "application/json") // Replace "@RequestMapping(method = RequestMethod.DELETE)" with "@DeleteMapping"
  Boolean deleteComment(@RequestHeader(value="x-auth-token") String token, @PathVariable("id") String id) {
    return Comment.delete(id);
  }
}

class CommentRequest implements Serializable {
  // Alterado por GFT AI Impact Bot
  private String username; // Make username a static final constant or non-public and provide accessors if needed.
  // Alterado por GFT AI Impact Bot
  private String body; // Make body a static final constant or non-public and provide accessors if needed.

  // Incluido por GFT AI Impact Bot
  public String getUsername() {
    return username;
  }

  // Incluido por GFT AI Impact Bot
  public String getBody() {
    return body;
  }
}

@ResponseStatus(HttpStatus.BAD_REQUEST)
class BadRequest extends RuntimeException {
  public BadRequest(String exception) {
    super(exception);
  }
}

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
class ServerError extends RuntimeException {
  public ServerError(String exception) {
    super(exception);
  }
}