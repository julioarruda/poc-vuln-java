

package com.scalesec.vulnado;

import org.apache.catalina.Server;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class Comment {
  // ...
  
  public static List<Comment> fetch_all() {
    Statement stmt = null;
    List<Comment> comments = new ArrayList();
    try {
      //...
    } catch (Exception e) {
      // Remover ou comentar as chamadas printStackTrace e println
      //e.printStackTrace();
      //System.err.println(e.getClass().getName()+": "+e.getMessage());
    } finally {
      return comments;
    }
  }

  public static Boolean delete(String id) {
    try {
      //...
    } catch(Exception e) {
      // Remover ou comentar a chamada printStackTrace
      //e.printStackTrace();
    } finally {
      return false;
    }
  }

  private Boolean commit() throws SQLException {
    //...
  }
}
