

package com.scalesec.vulnado;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cowsay {
  public static String run(String input) {
    ProcessBuilder processBuilder = new ProcessBuilder();
    
    // Adicione a verificação se o input contém apenas caracteres permitidos
    Pattern pattern = Pattern.compile("^[a-zA-Z0-9\\s]*$");
    Matcher matcher = pattern.matcher(input);
    if (!matcher.matches()) {
      throw new IllegalArgumentException("Caracteres inválidos no input");
    }

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
}
