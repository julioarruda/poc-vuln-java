

package com.scalesec.vulnado;

import java.security.SecureRandom;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

public class Comment {
  // ...

  public static Comment create(String username, String body) {
    long time = new Date().getTime();
    Timestamp timestamp = new Timestamp(time);
    Comment comment = new Comment(generateSecureRandomId(), username, body, timestamp);
    // ...
  }

  public static List<Comment> fetch_all() {
    PreparedStatement pstmt = null;
    List<Comment> comments = new ArrayList<>();
    try {
      Connection cxn = Postgres.connection();
      pstmt = cxn.prepareStatement("SELECT * FROM comments");
      ResultSet rs = pstmt.executeQuery();
      // ...
    }
    // ...
  }

  // Função adicional para gerar um ID seguro
  public static String generateSecureRandomId() {
    SecureRandom random = new SecureRandom();
    byte[] bytes = new byte[16];
    random.nextBytes(bytes);
    return new String(Base64.getEncoder().encode(bytes));
  }
  // ...
}
