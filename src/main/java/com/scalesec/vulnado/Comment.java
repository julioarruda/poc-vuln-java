package com.scalesec.vulnado;

// Removed unused import 'org.apache.catalina.Server'
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Logger; // Included by GFT AI Impact Bot

public class Comment {
  private static final Logger logger = Logger.getLogger(Comment.class.getName()); // Included by GFT AI Impact Bot
  private String id; // Altered by GFT AI Impact Bot
  private String username; // Altered by GFT AI Impact Bot
  private String body; // Altered by GFT AI Impact Bot
  private Timestamp createdOn; // Altered by GFT AI Impact Bot

  public Comment(String id, String username, String body, Timestamp createdOn) { // Altered by GFT AI Impact Bot
    this.id = id;
    this.username = username;
    this.body = body;
    this.createdOn = createdOn; // Altered by GFT AI Impact Bot
  }

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

  public static List<Comment> fetchAll() { // Altered by GFT AI Impact Bot
    Statement stmt = null;
    List<Comment> comments = new ArrayList<>();
    try (Connection cxn = Postgres.connection()) { // Altered by GFT AI Impact Bot
      stmt = cxn.createStatement();

      String query = "select * from comments;";
      ResultSet rs = stmt.executeQuery(query);
      while (rs.next()) {
        String id = rs.getString("id");
        String username = rs.getString("username");
        String body = rs.getString("body");
        Timestamp createdOn = rs.getTimestamp("created_on"); // Altered by GFT AI Impact Bot
        Comment c = new Comment(id, username, body, createdOn); // Altered by GFT AI Impact Bot
        comments.add(c);
      }
    } catch (Exception e) {
      logger.severe(e.getClass().getName()+": "+e.getMessage()); // Altered by GFT AI Impact Bot
    }
    return comments; // Altered by GFT AI Impact Bot
  }

  public static boolean delete(String id) { // Altered by GFT AI Impact Bot
    try (Connection con = Postgres.connection(); // Altered by GFT AI Impact Bot
         PreparedStatement pStatement = con.prepareStatement("DELETE FROM comments where id = ?")) { // Altered by GFT AI Impact Bot
      pStatement.setString(1, id);
      return 1 == pStatement.executeUpdate();
    } catch(Exception e) {
      logger.severe(e.getMessage()); // Altered by GFT AI Impact Bot
    }
    return false; // Altered by GFT AI Impact Bot
  }

  private boolean commit() throws SQLException { // Altered by GFT AI Impact Bot
    String sql = "INSERT INTO comments (id, username, body, created_on) VALUES (?,?,?,?)";
    try (Connection con = Postgres.connection(); // Altered by GFT AI Impact Bot
         PreparedStatement pStatement = con.prepareStatement(sql)) { // Altered by GFT AI Impact Bot
      pStatement.setString(1, this.id);
      pStatement.setString(2, this.username);
      pStatement.setString(3, this.body);
      pStatement.setTimestamp(4, this.createdOn); // Altered by GFT AI Impact Bot
      return 1 == pStatement.executeUpdate();
    }
  }
}