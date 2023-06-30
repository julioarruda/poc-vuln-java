-

package com.scalesec.vulnado;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Cowsay {
  public static String run(String input) {
    validateInput(input);
    ProcessBuilder processBuilder = new ProcessBuilder();
    String cmd = "/usr/games/cowsay '" + input + "'";
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

  private static void validateInput(String input) throws IllegalArgumentException {
    if (input == null) {
      throw new IllegalArgumentException("Input cannot be null");
    }
    String allowedCharacters = "[a-zA-Z0-9_\\s]*"; // permitir apenas letras, números, underscore e espaços
    if (!input.matches(allowedCharacters)) {
      throw new IllegalArgumentException("Invalid input: only letters, numbers, underscore and spaces are allowed");
    }
  }
}

