-

package com.scalesec.vulnado;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Cowsay {
  public static String run(String input) {
    ProcessBuilder processBuilder = new ProcessBuilder();
    // Sanitize and validate input before using it
    if (input == null || input.trim().isEmpty() || !input.matches("^[a-zA-Z0-9 ,.!?'\\\\-]+$")) {
      throw new IllegalArgumentException("Input contains invalid characters.");
    }
    
    // Use a list of arguments instead of concatenating a string
    processBuilder.command("bash", "-c", "/usr/games/cowsay", input);

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
