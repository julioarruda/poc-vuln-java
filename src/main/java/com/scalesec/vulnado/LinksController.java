package com.scalesec.vulnado;

import org.springframework.boot.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.*;
import java.util.List;
import java.io.Serializable;
import java.io.IOException;
import java.net.URL;

@RestController
@EnableAutoConfiguration
public class LinksController {
  @RequestMapping(value = "/links", produces = "application/json")
  List<String> links(@RequestParam String url) throws IOException {
    if (!isValidURL(url)) {
      throw new BadRequest("Invalid URL");
    }
    return LinkLister.getLinks(url);
  }

  @RequestMapping(value = "/links-v2", produces = "application/json")
  List<String> linksV2(@RequestParam String url) throws BadRequest {
    if (!isValidURL(url)) {
      throw new BadRequest("Invalid URL");
    }
    return LinkLister.getLinksV2(url);
  }

  private boolean isValidURL(String url) {
    /* Check if the URL is valid */
    try {
      new URL(url).toURI();
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}