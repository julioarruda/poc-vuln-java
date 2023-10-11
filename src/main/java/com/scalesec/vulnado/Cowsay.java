package com.scalesec.vulnado;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

public class Cowsay {
  public static String run(String input) {
    ProcessBuilder processBuilder = new ProcessBuilder();
    // Make sure that this user-controlled command argument doesn't lead to unwanted behavior
    String sanitizedInput = sanitizeInput(input);
    List<String> commands = new ArrayList<String>();
    commands.add("/usr/games/cowsay");
    commands.add(sanitizedInput);

    processBuilder.command(commands);
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
    // Make sure this debug feature is deactivated before delivering the code in production
    // System.out.println(cmd);
    return output.toString();
  }

  // sanitizeInput method to prevent unwanted behavior from user-controlled command argument
  private static String sanitizeInput(String input) {
    return input.replaceAll("[^a-zA-Z0-9 ]", "");
  }
}