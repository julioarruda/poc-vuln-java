

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
  
  private boolean isValidURL(String url) {
    String allowedDomain = "https://gft.com/";
    return (url != null) && url.startsWith(allowedDomain);
  }

  @RequestMapping(value = "/links", produces = "application/json")
  List<String> links(@RequestParam String url) throws IOException{
    if (isValidURL(url)) {
      return LinkLister.getLinks(url);
    } else {
      throw new BadRequest("Invalid URL provided");
    }
  }

  @RequestMapping(value = "/links-v2", produces = "application/json")
  List<String> linksV2(@RequestParam String url) throws BadRequest{
    if (isValidURL(url)) {
      return LinkLister.getLinksV2(url);
    } else {
      throw new BadRequest("Invalid URL provided");
    }
  }
}
