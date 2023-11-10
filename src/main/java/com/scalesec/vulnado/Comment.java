package com.scalesec.vulnado;

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Logger; // GFT AI Impact Bot - Replaced System.out with Logger

public class Comment {
  private static final Logger LOGGER = Logger.getLogger(Comment.class.getName()); // GFT AI Impact Bot - Created Logger

  private String id, username, body; // GFT AI Impact Bot - Made id, username, and body non-public
  private Timestamp createdOn; // GFT AI Impact Bot - Made created_on non-public and renamed to match regex

  public Comment(String id, String username, String body, Timestamp createdOn) {
    this.id = id;
    this.username = username;
    this.body = body;
    this.createdOn = createdOn; // GFT AI Impact Bot - Rename to match the regular expression
  }

  public String getId() { return this.id; } // GFT AI Impact Bot - Added accessor for id
  public String getUsername() { return this.username; } // GFT AI Impact Bot - Added accessor for username
  public String getBody() { return this.body; } // GFT AI Impact Bot - Added accessor for body
  public Timestamp getCreatedOn() { return this.createdOn; } // GFT AI Impact Bot - Added accessor for created_on

  public static Comment create(String username, String body){
    long time = new Date().getTime();
    Timestamp timestamp = new Timestamp(time);
    Comment comment = new Comment(UUID.randomUUID().toString(), username, body, timestamp);
    try {
      if (comment.commit()) {
        return comment;
      } else {
        throw new BadRequest("Unable to save comment");
      }
    } catch (Exception e) {
      throw new ServerError(e.getMessage());
    }
  }

  public static List<Comment> fetchAll() { // GFT AI Impact Bot - Renamed method to match regex
    List<Comment> comments = new ArrayList<>();
    try (Connection cxn = Postgres.connection(); Statement stmt = cxn.createStatement()) { // GFT AI Impact Bot - Used try-with-resources

      String query = "select * from comments;";
      ResultSet rs = stmt.executeQuery(query);
      while (rs.next()) {
        String id = rs.getString("id");
        String username = rs.getString("username");
        String body = rs.getString("body");
        Timestamp createdOn = rs.getTimestamp("created_on"); // GFT AI Impact Bot - Renamed to match the regular expression
        Comment c = new Comment(id, username, body, createdOn);
        comments.add(c);
      }
    } catch (Exception e) {
      LOGGER.severe(e.getMessage()); // GFT AI Impact Bot - Replaced System.err with Logger
    }
    return comments; // GFT AI Impact Bot - Moved return statement out of finally block
  }

  public static boolean delete(String id) { // GFT AI Impact Bot - Used primitive boolean
    try (Connection con = Postgres.connection(); PreparedStatement pStatement = con.prepareStatement("DELETE FROM comments where id = ?")) { // GFT AI Impact Bot - Used try-with-resources
      pStatement.setString(1, id);
      return 1 == pStatement.executeUpdate();
    } catch(Exception e) {
      LOGGER.severe(e.getMessage()); // GFT AI Impact Bot - Replaced e.printStackTrace() with Logger
    }
    return false; // GFT AI Impact Bot - Moved return statement out of finally block
  }

  private boolean commit() throws SQLException { // GFT AI Impact Bot - Used primitive boolean
    String sql = "INSERT INTO comments (id, username, body, created_on) VALUES (?,?,?,?)";
    try (Connection con = Postgres.connection(); PreparedStatement pStatement = con.prepareStatement(sql)) { // GFT AI Impact Bot - Used try-with-resources
      pStatement.setString(1, this.id);
      pStatement.setString(2, this.username);
      pStatement.setString(3, this.body);
      pStatement.setTimestamp(4, this.createdOn); // GFT AI Impact Bot - Renamed to match the regular expression
      return 1 == pStatement.executeUpdate();
    }
  }
}