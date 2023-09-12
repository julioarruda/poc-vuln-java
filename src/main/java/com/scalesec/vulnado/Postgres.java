


package com.scalesec.vulnado;
// ...

public class Postgres {

  // ...

  public static Connection connection() {
    try {
      // ...
    } catch (Exception e) {
      System.err.println(e.getMessage());
      System.exit(1);
    }
    return null;
  }

  public static void setup() {
    try {
      // ..
    } catch (Exception e) {
      System.err.println(e.getMessage());
      System.exit(1);
    }
  }

  // ...

  private static void insertUser(String username, String password) {
    // ...
    try {
      // ...
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  private static void insertComment(String username, String body) {
    // ...
    try {
      // ...
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
