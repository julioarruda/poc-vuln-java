

package com.scalesec.vulnado;

import org.springframework.boot.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.*;
import java.util.List;
import java.io.Serializable;
import java.io.IOException;


@RestController
@EnableAutoConfiguration
public class LinksController {
    
    @RequestMapping(value = "/links", produces = "application/json")
    List<String> links(@RequestParam String url) throws IOException {
        if (!isValidURL(url)) {
            throw new BadRequest("URL inválida");
        }
        return LinkLister.getLinks(url);
    }
    @RequestMapping(value = "/links-v2", produces = "application/json")
    List<String> linksV2(@RequestParam String url) throws BadRequest {
        return LinkLister.getLinksV2(url);
    }
    
    private boolean isValidURL(String url) {
        return url.startsWith("https://gft.com/br");
    }
}
