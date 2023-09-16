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
  List<String> links(@RequestParam String url) throws IOException{
    if(validateUrl(url)){
      return LinkLister.getLinks(url);
    } else {
      throw new BadRequest("Invalid URL");
    }
  }

  @RequestMapping(value = "/links-v2", produces = "application/json")
  List<String> linksV2(@RequestParam String url) throws BadRequest{
    if(validateUrl(url)){
      return LinkLister.getLinksV2(url);
    } else {
      throw new BadRequest("Invalid URL");
    }
  }

  private boolean validateUrl(String url){
    try{
      new URL(url).toURI();
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}