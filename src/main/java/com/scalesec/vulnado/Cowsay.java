package com.scalesec.vulnado;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class Cowsay {
  public static String run(String input) {
    ProcessBuilder processBuilder = new ProcessBuilder();
    String cmd = "/usr/games/cowsay";
    // Validate input to ensure it doesn't contain unwanted command
    if (input != null && !input.contains(";") && !input.contains("|") && !input.contains("&")) {
      cmd += " '" + input + "'";
    } else {
      throw new IllegalArgumentException("Invalid input!");
    }

    // Remove debug print statement
    // System.out.println(cmd);

    // Make sure the "PATH" used to find this command includes only what you intend.
    String path = Paths.get("/usr/games").toString();
    processBuilder.environment().put("PATH", path);

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