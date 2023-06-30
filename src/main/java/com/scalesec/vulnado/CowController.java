

package com.scalesec.vulnado;

import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.*;

import java.io.Serializable;

@RestController
@EnableAutoConfiguration
public class CowController {
    @RequestMapping(value = "/cowsay")
    String cowsay(@RequestParam(defaultValue = "I love Linux!") String input) {
        if (isValidInput(input)) {
            return Cowsay.run(input);
        } else {
            return "Entrada inválida";
        }
    }

    private boolean isValidInput(String input) {
        // Adicione aqui a lógica para validar a entrada do usuário.
    }
}
