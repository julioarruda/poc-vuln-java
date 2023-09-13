

package com.scalesec.vulnado;

import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.util.HtmlUtils;

import java.io.Serializable;

@RestController
@EnableAutoConfiguration
public class CowController {
    @RequestMapping(value = "/cowsay", method = {RequestMethod.GET, RequestMethod.POST})
    String cowsay(@RequestParam(defaultValue = "I love Linux!") String input) {
        return Cowsay.run(HtmlUtils.htmlEscape(input));
    }
}

Observe que a função `HtmlUtils.htmlEscape()` foi usada para escapar o input a fim de reduzir a possibilidade de um ataque XSS, e os métodos GET e POST foram explicitamente definidos como permitidos para a rota "/cowsay".