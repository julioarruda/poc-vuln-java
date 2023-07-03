

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
    if (!isTrustedURL(url)) {
      throw new BadRequest("URL não confiável.");
    }
    return LinkLister.getLinks(url);
  }

  @RequestMapping(value = "/links-v2", produces = "application/json")
  List<String> linksV2(@RequestParam String url) throws BadRequest {
    if (!isTrustedURL(url)) {
      throw new BadRequest("URL não confiável.");
    }
    return LinkLister.getLinksV2(url);
  }

  // Método para verificar se a URL é confiável
  private boolean isTrustedURL(String url) {
    // Lista de URLs confiáveis
    String[] trustedURLs = {"https://gft.com/br"};

    for (String trustedURL : trustedURLs) {
      if (url.equals(trustedURL)) {
        return true;
      }
    }
    return false;
  }
}
