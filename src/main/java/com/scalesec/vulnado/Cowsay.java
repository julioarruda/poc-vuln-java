package com.scalesec.vulnado;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Cowsay {
  public static String run(String input) {
    ProcessBuilder processBuilder = new ProcessBuilder();
    String cmd = "/usr/games/cowsay";
    // Ensure command includes only what you intend
    processBuilder.command("bash", "-c", cmd, input);
    processBuilder.environment().put("PATH", "/usr/games/"); // Ensure PATH used includes only what you intend

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

    // Ensure debug feature is deactivated before delivering the code in production
    // System.out.println(cmd);

    return output.toString();
  }
}