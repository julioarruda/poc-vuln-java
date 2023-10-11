package com.scalesec.vulnado;

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Logger;

public class Comment {
  private static final String ID;
  private static final String USERNAME;
  private static final String BODY;
  private static final Timestamp CREATED_ON;

  public Comment(String id, String username, String body, Timestamp created_on) {
    this.ID = id;
    this.USERNAME = username;
    this.BODY = body;
    this.CREATED_ON = created_on;
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

  public static List<Comment> fetchAll() {
    List<Comment> comments = new ArrayList();
    try (Connection cxn = Postgres.connection();
         Statement stmt = cxn.createStatement()) {
      String query = "select * from comments;";
      ResultSet rs = stmt.executeQuery(query);
      while (rs.next()) {
        String id = rs.getString("id");
        String username = rs.getString("username");
        String body = rs.getString("body");
        Timestamp created_on = rs.getTimestamp("created_on");
        Comment c = new Comment(id, username, body, created_on);
        comments.add(c);
      }
    } catch (Exception e) {
      Logger.getLogger(Comment.class.getName()).log(Level.SEVERE, null, e);
    }
    return comments;
  }

  public static boolean delete(String id) {
    try (Connection con = Postgres.connection();
         PreparedStatement pStatement = con.prepareStatement("DELETE FROM comments where id = ?")) {
      pStatement.setString(1, id);
      return 1 == pStatement.executeUpdate();
    } catch(Exception e) {
      Logger.getLogger(Comment.class.getName()).log(Level.SEVERE, null, e);
      return false;
    }
  }

  private boolean commit() throws SQLException {
    try (Connection con = Postgres.connection();
         PreparedStatement pStatement = con.prepareStatement("INSERT INTO comments (id, username, body, created_on) VALUES (?,?,?,?)")) {
      pStatement.setString(1, this.ID);
      pStatement.setString(2, this.USERNAME);
      pStatement.setString(3, this.BODY);
      pStatement.setTimestamp(4, this.CREATED_ON);
      return 1 == pStatement.executeUpdate();
    }
  }
}