package com.scalesec.vulnado;

import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.*;

// Removed unused import 'java.io.Serializable'; // Alterado por GFT AI Impact Bot

@RestController
@EnableAutoConfiguration
public class CowController {
    @RequestMapping(value = "/cowsay", method = RequestMethod.GET) // Make sure allowing safe and unsafe HTTP methods is safe here. // Alterado por GFT AI Impact Bot
    String cowsay(@RequestParam(defaultValue = "I love Linux!") String input) {
        return Cowsay.run(input);
    }
}