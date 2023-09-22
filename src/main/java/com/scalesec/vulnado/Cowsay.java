package com.scalesec.vulnado;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Cowsay {
    public static String run(String input) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        
        // Evita injeção de comando
        String cmd = "/usr/games/cowsay";
        processBuilder.command("bash", "-c", cmd, input);

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