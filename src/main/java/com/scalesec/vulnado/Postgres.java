

public class Postgres {
    private static final boolean DEBUG = Boolean.parseBoolean(System.getenv("DEBUG"));

    public static void main(String[] args) {
        if (DEBUG) {
            setup();
        }
    }

    ...
}

