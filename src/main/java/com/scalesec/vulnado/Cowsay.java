package com.scalesec.vulnado;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Cowsay {
  public static String run(String input) {
    // Correção da vulnerabilidade: Certifique-se de que este argumento de comando controlado pelo usuário não leve a um comportamento indesejado.
    // Utilizamos a função escapeJava() da biblioteca Apache Commons Text para escapar caracteres especiais no input.
    String sanitizedInput = org.apache.commons.text.StringEscapeUtils.escapeJava(input);

    ProcessBuilder processBuilder = new ProcessBuilder();
    String cmd = "/usr/games/cowsay '" + sanitizedInput + "'";

    // Correção da vulnerabilidade: Certifique-se de que este recurso de depuração esteja desativado antes de entregar o código em produção.
    // Removemos a linha que imprime o comando na console.
    
    processBuilder.command("bash", "-c", cmd);

    // Correção da vulnerabilidade: Certifique-se de que o "PATH" usado para encontrar este comando inclui apenas o que você pretende.
    // Definimos explicitamente o PATH no ProcessBuilder.
    processBuilder.environment().put("PATH", "/usr/games");

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