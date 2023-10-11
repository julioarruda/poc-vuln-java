package com.scalesec.vulnado;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class Cowsay {
  private static final Logger LOGGER = Logger.getLogger(Cowsay.class.getName());

  private Cowsay() {
    // Private constructor to hide the implicit public one.
  }

  public static String run(String input) {
    ProcessBuilder processBuilder = new ProcessBuilder();
    String cmd = "/usr/games/cowsay '" + input.replace("'", "\\'") + "'"; // Make sure that this user-controlled command argument doesn't lead to unwanted behavior.

    LOGGER.info(cmd); // Replace this use of System.out or System.err by a logger.

    processBuilder.command("bash", "-c", cmd);
    processBuilder.environment().put("PATH", "/usr/games"); // Make sure the "PATH" used to find this command includes only what you intend.

    StringBuilder output = new StringBuilder();

    try {
      Process process = processBuilder.start();
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

      String line;
      while ((line = reader.readLine()) != null) {
        output.append(line + "\n");
      }
    } catch (Exception e) {
      LOGGER.severe(e.getMessage());
    }
    return output.toString();
  }
}