

package com.scalesec.vulnado;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;

public class Cowsay {
  public static String run(String input) {
    CommandLine cmdLine = new CommandLine("cowsay");
    cmdLine.addArgument(input, false);
    System.out.println(cmdLine.toString());

    StringBuilder output = new StringBuilder();

    try {
      DefaultExecutor executor = new DefaultExecutor();
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

