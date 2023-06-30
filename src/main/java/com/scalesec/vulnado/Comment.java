

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
    // ...
    } catch (Exception e) {
      // Remova a linha abaixo e substitua por um mecanismo de registro adequado
      //e.printStackTrace();
      System.err.println(e.getClass().getName()+": "+e.getMessage());
    } finally {
      return comments;
    }
  }

  public static Boolean delete(String id) {
    // ...
    } catch(Exception e) {
      // Remova a linha abaixo e substitua por um mecanismo de registro adequado
      //e.printStackTrace();
    } finally {
      return false;
    }
  }

  private Boolean commit() throws SQLException {
    // ...
  }
}

