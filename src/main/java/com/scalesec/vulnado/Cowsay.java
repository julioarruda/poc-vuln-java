

package com.scalesec.vulnado;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Cowsay {
    public static String run(String input) {
        String[] bannedTerms = {";", "&&", "|", "&", "$", "`", "\\", "\n"};
        Stream<String> bannedStream = Stream.of(bannedTerms);
        boolean isSafe = bannedStream.noneMatch(input::contains);

        if (!isSafe)
            throw new IllegalArgumentException("Unsafe input provided");

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
