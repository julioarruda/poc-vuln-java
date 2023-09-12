------

public static void setup(){
    try {
        Connection c = connection();
        Statement stmt = c.createStatement();

        // Create Schema
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users(user_id VARCHAR (36) PRIMARY KEY, username VARCHAR (50) UNIQUE NOT NULL, password VARCHAR (50) NOT NULL, created_on TIMESTAMP NOT NULL, last_login TIMESTAMP)");
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS comments(id VARCHAR (36) PRIMARY KEY, username VARCHAR (36), body VARCHAR (500), created_on TIMESTAMP NOT NULL)");

        // Clean up any existing data
        stmt.executeUpdate("DELETE FROM users");
        stmt.executeUpdate("DELETE FROM comments");

        // Insert seed data
        insertUser("admin", "!!SuperSecretAdmin!!");
        insertUser("alice", "AlicePassword!");
        insertUser("bob", "BobPassword!");
        insertUser("eve", "$EVELknev^l");
        insertUser("rick", "!GetSchwifty!");

        insertComment("rick", "cool dog m8");
        insertComment("alice", "OMG so cute!");
        c.close();
    } catch (Exception e) {
        System.out.println(e);
        System.exit(1);
    }
}
