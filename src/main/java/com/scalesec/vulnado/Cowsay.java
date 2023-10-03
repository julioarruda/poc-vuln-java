package com.scalesec.vulnado;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Cowsay {
  public static String run(String input) {
    ProcessBuilder processBuilder = new ProcessBuilder();
    // Correção da Vulnerabilidade: Garantindo que o comando controlado pelo usuário não leve a comportamentos indesejados.
    // Para isso, vamos sanitizar a entrada do usuário para evitar a execução de comandos indesejados
    String sanitizedInput = input.replaceAll("[^a-zA-Z0-9 ]", "");
    
    String cmd = "/usr/games/cowsay '" + sanitizedInput + "'";
    
    // Correção da Vulnerabilidade: Garantir que este recurso de debug esteja desativado antes de entregar o código em produção.
    // Vamos comentar a linha de impressão do comando
    // System.out.println(cmd);
    
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