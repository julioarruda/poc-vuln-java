

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

  UrlValidator urlValidator = new UrlValidator();

  @RequestMapping(value = "/links", method = RequestMethod.GET, produces = "application/json")
  List<String> links(@RequestParam String url) throws IOException{
    if(!urlValidator.isValid(url)){
      throw new BadRequestException("Url fornecida inválida!");  
    }
    return LinkLister.getLinks(url);
  }
  
  @RequestMapping(value = "/links-v2", method = RequestMethod.GET, produces = "application/json")
  List<String> linksV2(@RequestParam String url) throws BadRequest{
    if(!urlValidator.isValid(url)){
      throw new BadRequestException("Url fornecida inválida!");  
    }
    return LinkLister.getLinksV2(url);
  }
}
