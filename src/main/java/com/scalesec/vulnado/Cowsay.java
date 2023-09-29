package com.scalesec.vulnado;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Cowsay {
  public static String run(String input) {
    // Corrigindo a vulnerabilidade de injeção de comando:
    // Garantindo que o input do usuário não leve a um comportamento indesejado
    // Escapando caracteres potencialmente perigosos no input
    String sanitizedInput = input.replaceAll("[^a-zA-Z0-9 ]", "");

    ProcessBuilder processBuilder = new ProcessBuilder();
    String cmd = "/usr/games/cowsay '" + sanitizedInput + "'";

    // Corrigindo a vulnerabilidade de depuração:
    // Certificando-se de que o recurso de depuração esteja desativado antes de entregar o código em produção
    // Removendo a linha System.out.println(cmd);
    
    // Corrigindo a vulnerabilidade do caminho:
    // Certificando-se de que o "PATH" usado para encontrar este comando inclua apenas o que você pretende
    // Utilizando caminho absoluto para o comando
    processBuilder.command("/bin/bash", "-c", cmd);

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