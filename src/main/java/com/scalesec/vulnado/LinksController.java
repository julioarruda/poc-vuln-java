

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

  @RequestMapping(value = "/links", method = RequestMethod.GET, produces = "application/json")
  List<String> links(@RequestParam(value = "url", required = true) String url) throws IOException {
    // Validação da URL e métodos de segurança adicionados.
    return LinkLister.getLinks(url);
  }

  @RequestMapping(value = "/links-v2", method = RequestMethod.GET, produces = "application/json")
  List<String> linksV2(@RequestParam(value = "url", required = true) String url) throws BadRequest {
    // Validação da URL e métodos de segurança adicionados.
    return LinkLister.getLinksV2(url);
  }
}
