--
No Código:


package com.scalesec.vulnado;

import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.*;

import java.io.Serializable;

@RestController
@EnableAutoConfiguration
public class CowController {

    @RequestMapping(value = "/cowsay", method = RequestMethod.GET)
    String cowsay(@RequestParam(defaultValue = "I love Linux!") String input) {
        return Cowsay.run(input);
    }
}

Estamos agora limitando este endpoint para que apenas aceite requisições do tipo GET, removendo assim a vulnerabilidade.