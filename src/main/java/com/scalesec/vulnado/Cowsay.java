package com.scalesec.vulnado;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Cowsay {
  public static String run(String input) {
    ProcessBuilder processBuilder = new ProcessBuilder();
    // Sanitize the input to avoid command injection attacks
    String sanitizedInput = input.replaceAll("[^a-zA-Z0-9 ]", "");
    String cmd = "/usr/games/cowsay '" + sanitizedInput + "'";
    
    // Remove debug statement for production
    // System.out.println(cmd);
    
    // Specify the direct path to the command to ensure only the intended command is used
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
}