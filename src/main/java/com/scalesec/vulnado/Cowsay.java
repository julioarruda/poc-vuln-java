package com.scalesec.vulnado;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Cowsay {
  public static String run(String input) {
    ProcessBuilder processBuilder = new ProcessBuilder();
    
    // Corrigindo a vulnerabilidade do comando controlado pelo usuário
    List<String> commands = new ArrayList<String>();
    commands.add("/usr/games/cowsay");
    commands.add(input);
    
    // Corrigindo a vulnerabilidade de caminho
    processBuilder.environment().put("PATH", "/usr/games");
    
    // Corrigindo a vulnerabilidade do recurso de depuração
    boolean debug = false;
    if(debug) {
      System.out.println(commands);
    }
    
    processBuilder.command(commands);

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