package com.scalesec.vulnado;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class Cowsay {
  public static String run(String input) {
    ProcessBuilder processBuilder = new ProcessBuilder();
    String cmd = "/usr/games/cowsay";
    String sanitizedInput = sanitizeInput(input);
    
    // Remove the debug print statement
    // System.out.println(cmd);

    processBuilder.command("bash", "-c", Paths.get(cmd, sanitizedInput).toString());
    processBuilder.environment().put("PATH", "/usr/games");

    StringBuilder output = new StringBuilder();

    try {
      Process process = processBuilder.start();
      BufferedReader reader = 
             new BufferedReader(new InputStreamReader(process.getInputStream()));

      String line;
      while ((line = reader.readLine()) != null) {
        output.append(line + "\n");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return output.toString();
  }

  // Method to sanitize the user input
  private static String sanitizeInput(String input) {
    return input.replaceAll("[^a-zA-Z0-9]", "");
  }
}