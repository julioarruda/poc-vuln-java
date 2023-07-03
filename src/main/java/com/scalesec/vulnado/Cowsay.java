

package com.scalesec.vulnado;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Cowsay {
  public static String run(String input) {
    ProcessBuilder processBuilder = new ProcessBuilder();
    
    // Sanitize user input to prevent command injection
    String sanitizedInput = input.replaceAll("[^a-zA-Z0-9_!@#$%^&*()\\-+=<>?\\/\\.,;:'\"\\[\\]\\{\\}|\\\\]", "");
    String cmd = "/usr/games/cowsay '" + sanitizedInput + "'";
    
    System.out.println(cmd);
    processBuilder.command("bash", "-c", cmd);

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
