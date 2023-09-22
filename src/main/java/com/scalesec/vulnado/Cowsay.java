package com.scalesec.vulnado;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Cowsay {

    private static final Pattern SAFE_PATTERN = Pattern.compile("[a-zA-Z0-9 ]*");

    public static String run(String input) {
        if (!SAFE_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException("Invalid input!");
        }

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
}