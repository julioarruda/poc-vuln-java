

package com.scalesec.vulnado;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cowsay {
  public static String run(String input) {
    ProcessBuilder processBuilder = new ProcessBuilder();
    List<String> commands = new ArrayList<>(Arrays.asList("/usr/games/cowsay"));
    if (input.matches("[A-Za-z0-9\\s]+")) {
      commands.addAll(Arrays.asList(input.split(" ")));
      processBuilder.command(commands);
    } else {
      throw new IllegalArgumentException("Invalid input.");
    }

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
