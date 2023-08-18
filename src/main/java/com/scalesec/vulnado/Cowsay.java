--------------------

package com.scalesec.vulnado;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Cowsay {
  public static String run(String input) {
    ProcessBuilder processBuilder = new ProcessBuilder();
    
    // Adicione uma lista de caracteres permitidos
    Pattern allowedCharacters = Pattern.compile("[a-zA-Z0-9 .,:;!?$'\"()-]+");
    
    // Verifique se a entrada do usuário contém apenas caracteres permitidos
    if (!allowedCharacters.matcher(input).matches()) {
      throw new IllegalArgumentException("Entrada inválida. Por favor, use apenas caracteres alfanuméricos e pontuação.");
    }

    String[] cmd = { "/usr/games/cowsay", input };
    processBuilder.command(cmd);

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
