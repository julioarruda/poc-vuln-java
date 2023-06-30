

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
  @RequestMapping(value = "/links", produces = "application/json", method = RequestMethod.GET)
  List<String> links(@RequestParam("url") String url) throws IOException {
    return LinkLister.getLinks(url);
  }
  @RequestMapping(value = "/links-v2", produces = "application/json", method = RequestMethod.GET)
  List<String> linksV2(@RequestParam("url") String url) throws BadRequest {
    return LinkLister.getLinksV2(url);
  }
}
