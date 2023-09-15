---------------------


public static User fetch(String un) {
    Statement stmt = null;
    User user = null;
    try {
      Connection cxn = Postgres.connection();
      stmt = cxn.createStatement();

      String query = "select * from users where username = '" + un + "' limit 1";

      ResultSet rs = stmt.executeQuery(query);
      if (rs.next()) {
        String user_id = rs.getString("user_id");
        String username = rs.getString("username");
        String password = rs.getString("password");
        user = new User(user_id, username, password);
      }
      cxn.close();
    } catch (Exception e) {      
      throw new RuntimeException(e.getClass().getName()+": "+e.getMessage(), e);
    } finally {
      return user;
    }
  }
