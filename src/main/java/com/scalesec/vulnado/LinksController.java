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
    URL u = new URL(url);
    return LinkLister.getLinks(u.toString());
  }
  @RequestMapping(value = "/links-v2", produces = "application/json")
  List<String> linksV2(@RequestParam String url) throws BadRequest{
    URL u = new URL(url);
    return LinkLister.getLinksV2(u.toString());
  }
}