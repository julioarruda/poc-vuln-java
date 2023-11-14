package com.scalesec.vulnado;

// Removed unused import 'org.apache.catalina.Server'; CVE-Alterado por GFT AI Impact Bot
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class Comment {
  private static final String id; // Make id a static final constant or non-public and provide accessors if needed. CVE-Alterado por GFT AI Impact Bot
  private static final String username; // Make username a static final constant or non-public and provide accessors if needed. CVE-Alterado por GFT AI Impact Bot
  private static final String body; // Make body a static final constant or non-public and provide accessors if needed. CVE-Alterado por GFT AI Impact Bot
  private static final Timestamp createdOn; // Make created_on a static final constant or non-public and provide accessors if needed. CVE-Alterado por GFT AI Impact Bot
  // Rename this field "created_on" to match the regular expression '^[a-z][a-zA-Z0-9]*$'. CVE-Alterado por GFT AI Impact Bot

  public Comment(String id, String username, String body, Timestamp createdOn) {
    this.id = id;
    this.username = username;
    this.body = body;
    this.createdOn = createdOn;
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

  public static List<Comment> fetchAll() { // Rename this method name to match the regular expression '^[a-z][a-zA-Z0-9]*$'. CVE-Alterado por GFT AI Impact Bot
    Statement stmt = null;
    List<Comment> comments = new ArrayList<>(); // Provide the parametrized type for this generic. CVE-Alterado por GFT AI Impact Bot
    try {
      Connection cxn = Postgres.connection();
      stmt = cxn.createStatement();

      String query = "select * from comments;";
      ResultSet rs = stmt.executeQuery(query);
      while (rs.next()) {
        String id = rs.getString("id");
        String username = rs.getString("username");
        String body = rs.getString("body");
        Timestamp createdOn = rs.getTimestamp("created_on"); // Rename this local variable to match the regular expression '^[a-z][a-zA-Z0-9]*$'. CVE-Alterado por GFT AI Impact Bot
        Comment c = new Comment(id, username, body, createdOn);
        comments.add(c);
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName()+": "+e.getMessage());
    } finally {
      if(stmt != null) stmt.close(); // Use try-with-resources or close this "Statement" in a "finally" clause. CVE-Alterado por GFT AI Impact Bot
    }
    return comments; // Remove this return statement from this finally block. CVE-Alterado por GFT AI Impact Bot
  }

  public static boolean delete(String id) { // Use a primitive boolean expression here. CVE-Alterado por GFT AI Impact Bot
    PreparedStatement pStatement = null; // Refactor this method to not always return the same value. CVE-Alterado por GFT AI Impact Bot
    try {
      String sql = "DELETE FROM comments where id = ?";
      Connection con = Postgres.connection();
      pStatement = con.prepareStatement(sql);
      pStatement.setString(1, id);
      return 1 == pStatement.executeUpdate();
    } catch(Exception e) {
      e.printStackTrace();
    } finally {
      if(pStatement != null) pStatement.close(); // Use try-with-resources or close this "PreparedStatement" in a "finally" clause. CVE-Alterado por GFT AI Impact Bot
    }
    return false; // Refactor this method to not always return the same value. CVE-Alterado por GFT AI Impact Bot
  }

  private boolean commit() throws SQLException { // Use a primitive boolean expression here. CVE-Alterado por GFT AI Impact Bot
    PreparedStatement pStatement = null; // Refactor this method to not always return the same value. CVE-Alterado por GFT AI Impact Bot
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
      if(pStatement != null) pStatement.close(); // Use try-with-resources or close this "PreparedStatement" in a "finally" clause. CVE-Alterado por GFT AI Impact Bot
    }
  }
}