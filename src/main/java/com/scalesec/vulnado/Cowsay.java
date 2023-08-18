

package com.scalesec.vulnado;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Cowsay {
  public static String run(String input) {
    ProcessBuilder processBuilder = new ProcessBuilder();
    String sanitizedInput = validateInput(input);
    System.out.println(sanitizedInput);
    processBuilder.command("bash", "-c", "/usr/games/cowsay", sanitizedInput);

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

  public static String validateInput(String input) {
    return input.replaceAll("[^a-zA-Z0-9,. ]", "");
  }
}
