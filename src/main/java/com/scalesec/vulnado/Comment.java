package com.scalesec.vulnado;

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Logger;

public class Comment {
  private static final Logger LOGGER = Logger.getLogger(Comment.class.getName());

  private static final String ID = "id";
  private static final String USERNAME = "username";
  private static final String BODY = "body";
  private static final String CREATED_ON = "created_on";

  private String id, username, body;
  private Timestamp createdOn;

  public Comment(String id, String username, String body, Timestamp created_on) {
    this.id = id;
    this.username = username;
    this.body = body;
    this.createdOn = created_on;
  }

  public static Comment create(String username, String body){
    long time = new Date().getTime();
    Timestamp timestamp = new Timestamp(time);
    Comment comment = new Comment(UUID.randomUUID().toString(), username, body, timestamp);
    try {
      if (commit(comment)) {
        return comment;
      } else {
        throw new BadRequest("Unable to save comment");
      }
    } catch (Exception e) {
      throw new ServerError(e.getMessage());
    }
  }

  public static List<Comment> fetchAll() {
    List<Comment> comments = new ArrayList<>();
    try (Connection cxn = Postgres.connection();
         Statement stmt = cxn.createStatement()) {

      String query = "select * from comments;";
      ResultSet rs = stmt.executeQuery(query);
      while (rs.next()) {
        String id = rs.getString(ID);
        String username = rs.getString(USERNAME);
        String body = rs.getString(BODY);
        Timestamp created_on = rs.getTimestamp(CREATED_ON);
        Comment c = new Comment(id, username, body, created_on);
        comments.add(c);
      }
    } catch (Exception e) {
      LOGGER.severe(e.getMessage());
    }
    return comments;
  }

  public static boolean delete(String id) {
    try (Connection con = Postgres.connection();
         PreparedStatement pStatement = con.prepareStatement("DELETE FROM comments where id = ?")) {
      pStatement.setString(1, id);
      return 1 == pStatement.executeUpdate();
    } catch(Exception e) {
      LOGGER.severe(e.getMessage());
    }
    return false;
  }

  private static boolean commit(Comment comment) throws SQLException {
    String sql = "INSERT INTO comments (id, username, body, created_on) VALUES (?,?,?,?)";
    try (Connection con = Postgres.connection();
         PreparedStatement pStatement = con.prepareStatement(sql)) {
      pStatement.setString(1, comment.id);
      pStatement.setString(2, comment.username);
      pStatement.setString(3, comment.body);
      pStatement.setTimestamp(4, comment.createdOn);
      return 1 == pStatement.executeUpdate();
    }
  }
}