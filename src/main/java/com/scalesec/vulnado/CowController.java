package com.scalesec.vulnado;

import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.*;

import java.io.Serializable;

@RestController
@EnableAutoConfiguration
public class CowController {
    @RequestMapping(value = "/cowsay")
    String cowsay(@RequestParam(defaultValue = "I love Linux!") String input) {
        return Cowsay.run(input);
    }
    
    private static class Cowsay {
        static String run(String input) {
            if (input.contains(";") || input.contains("&") || input.contains("|")) {
                throw new IllegalArgumentException("Invalid input");
            }
            
            return " _________________\n< " + input + " >\n -----------------\n        \\   ^__^\n         \\  (oo)\\_______\n            (__)\\       )\\/\\\n                ||----w |\n                ||     ||";
        }
    }
}