package com.scalesec.vulnado;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Cowsay {
  public static String run(String input) {
    ProcessBuilder processBuilder = new ProcessBuilder();
    // Make sure that this user-controlled command argument doesn't lead to unwanted behavior
    String sanitizedInput = sanitizeInput(input);
    String cmd = "/usr/games/cowsay '" + sanitizedInput + "'";

    // Make sure this debug feature is deactivated before delivering the code in production.
    // System.out.println(cmd);
    
    // Make sure the "PATH" used to find this command includes only what you intend.
    processBuilder.command("/bin/bash", "-c", cmd);

    StringBuilder output = new StringBuilder();

    try {
      Process process = processBuilder.start();
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

      String line;
      while ((line = reader.readLine()) != null) {
        output.append(line + "\n");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return output.toString();
  }

  private static String sanitizeInput(String input) {
    // Remove any unwanted characters or commands
    return input.replaceAll("[^\\w\\s]","");
  }
}
