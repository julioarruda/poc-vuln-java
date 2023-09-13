

public static User fetch(String un) {
  Statement stmt = null;
  User user = null;
  try {
    Connection cxn = Postgres.connection();
    stmt = cxn.createStatement();
    //System.out.println("Opened database successfully");  // Commented out

    String query = "select * from users where username = '" + un + "' limit 1";
    // System.out.println(query);     // Commented out

    ResultSet rs = stmt.executeQuery(query);
    if (rs.next()) {
      String user_id = rs.getString("user_id");
      String username = rs.getString("username");
      String password = rs.getString("password");
      user = new User(user_id, username, password);
    }
    cxn.close();
  } catch (Exception e) {
    e.printStackTrace();
    System.err.println(e.getClass().getName()+": "+e.getMessage());
  } finally {
    return user;
  }
}
