package com.scalesec.vulnado;

// Removed unused import
// import org.apache.catalina.Server;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class Comment {
  // Made the fields non-public and provided accessors
  private String id, username, body;
  private Timestamp createdOn; // Renamed field to match the regular expression '^[a-z][a-zA-Z0-9]*$'

  public Comment(String id, String username, String body, Timestamp createdOn) {
    this.id = id;
    this.username = username;
    this.body = body;
    this.createdOn = createdOn;
  }
  
  // Accessors
  public String getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getBody() {
    return body;
  }

  public Timestamp getCreatedOn() {
    return createdOn;
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

  public static List<Comment> fetch_all() {
    Statement stmt = null;
    List<Comment> comments = new ArrayList<>();
    try {
      Connection cxn = Postgres.connection();
      stmt = cxn.createStatement();

      String query = "select * from comments;";
      ResultSet rs = stmt.executeQuery(query);
      while (rs.next()) {
        String id = rs.getString("id");
        String username = rs.getString("username");
        String body = rs.getString("body");
        Timestamp createdOn = rs.getTimestamp("created_on"); // Renamed variable to match the regular expression '^[a-z][a-zA-Z0-9]*$'
        Comment c = new Comment(id, username, body, createdOn);
        comments.add(c);
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName()+": "+e.getMessage());
    } finally {
      // Closed Statement in finally block
      try {
        if (stmt != null) {
          stmt.close();
        }
      } catch (SQLException se) {
        se.printStackTrace();
      }
    }
    return comments; // Removed return statement from finally block
  }

  public static boolean delete(String id) { // Used a primitive boolean expression here
    PreparedStatement pStatement = null; // Changed Statement to PreparedStatement
    try {
      String sql = "DELETE FROM comments where id = ?";
      Connection con = Postgres.connection();
      pStatement = con.prepareStatement(sql);
      pStatement.setString(1, id);
      return 1 == pStatement.executeUpdate();
    } catch(Exception e) {
      e.printStackTrace();
    } finally {
      // Closed PreparedStatement in finally block
      try {
        if (pStatement != null) {
          pStatement.close();
        }
      } catch (SQLException se) {
        se.printStackTrace();
      }
    }
    return false; // Refactored method to not always return the same value
  }

  private boolean commit() throws SQLException { // Used a primitive boolean expression here
    PreparedStatement pStatement = null; // Changed Statement to PreparedStatement
    try {
      String sql = "INSERT INTO comments (id, username, body, created_on) VALUES (?,?,?,?)";
      Connection con = Postgres.connection();
      pStatement = con.prepareStatement(sql);
      pStatement.setString(1, this.id);
      pStatement.setString(2, this.username);
      pStatement.setString(3, this.body);
      pStatement.setTimestamp(4, this.createdOn);
      return 1 == pStatement.executeUpdate();
    } finally {
      // Closed PreparedStatement in finally block
      try {
        if (pStatement != null) {
          pStatement.close();
        }
      } catch (SQLException se) {
        se.printStackTrace();
      }
    }
  }
}