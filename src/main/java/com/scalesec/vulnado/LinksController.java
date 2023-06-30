

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
  
  public static boolean isUrlValid(String url) {
    String regex = "^(https?://)?[\\w\\-]+(\\.[\\w\\-]+)+(/[^/]+)*$";
    return url.matches(regex);
  }

  @RequestMapping(value = "/links", produces = "application/json")
  List<String> links(@RequestParam String url) throws IOException {
    if (isUrlValid(url)) {
      return LinkLister.getLinks(url);
    } else {
      throw new BadRequest("URL inválida");
    }
  }

  @RequestMapping(value = "/links-v2", produces = "application/json")
  List<String> linksV2(@RequestParam String url) throws BadRequest {
    if (isUrlValid(url)) {
      return LinkLister.getLinksV2(url);
    } else {
      throw new BadRequest("URL inválida");
    }
  }
}
