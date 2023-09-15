package com.scalesec.vulnado;

import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.*;
import java.util.regex.Pattern;
import java.io.Serializable;

@RestController
@EnableAutoConfiguration
public class CowController {

    private static final Pattern ALLOWED_CHARACTERS = Pattern.compile("[a-zA-Z0-9\\s]*");

    @RequestMapping(value = "/cowsay")
    String cowsay(@RequestParam(defaultValue = "I love Linux!") String input) {
        if (!ALLOWED_CHARACTERS.matcher(input).matches()) {
            throw new IllegalArgumentException("Invalid characters in input");
        }
        return Cowsay.run(input);
    }
}