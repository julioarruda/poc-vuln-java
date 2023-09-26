package com.scalesec.vulnado;

import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.cors.CorsConfiguration;
import java.util.Arrays;
import java.io.Serializable;

@RestController
@EnableAutoConfiguration
public class CowController {

    private static final CorsConfiguration configuration = new CorsConfiguration();
    static {
        configuration.setAllowedMethods(Arrays.asList(HttpMethod.GET.name(), HttpMethod.POST.name()));
    }

    @RequestMapping(value = "/cowsay")
    ResponseEntity<String> cowsay(@RequestParam(defaultValue = "I love Linux!") String input) {
        return ResponseEntity.ok().headers(responseHeaders -> responseHeaders.setAccessControlAllowOrigin(configuration)).body(Cowsay.run(input));
    }

    static class Cowsay {
        static String run(String input) {
            // TBD
            return input;
        }
    }
}