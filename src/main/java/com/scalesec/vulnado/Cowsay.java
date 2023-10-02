package com.scalesec.vulnado;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Cowsay {
  public static String run(String input) {
    ProcessBuilder processBuilder = new ProcessBuilder();
    // Sanitizing the input to avoid unwanted command execution
    String sanitizedInput = sanitizeInput(input);
    
    String cmd = "/usr/games/cowsay '" + sanitizedInput + "'";
    
    // Commented out the debug line
    // System.out.println(cmd);
    
    processBuilder.command("bash", "-c", cmd);
    processBuilder.directory(new File("/usr/games/")); // Setting the PATH to find the command

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
  
  // Method to sanitize the input
  public static String sanitizeInput(String input) {
    return input.replaceAll("[^a-zA-Z0-9 ]", "");  // Removing any non-alphanumeric characters
  }
}