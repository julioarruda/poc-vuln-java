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
  List<String> links(@RequestParam String url) throws IOException{
    // Verificar se a URL é válida antes de chamar o método
    if (!isValidUrl(url)) {
      throw new BadRequestException("URL inválida");
    }
    
    return LinkLister.getLinks(url);
  }
  
  @RequestMapping(value = "/links-v2", produces = "application/json")
  List<String> linksV2(@RequestParam String url) throws BadRequest{
    // Verificar se a URL é válida antes de chamar o método
    if (!isValidUrl(url)) {
      throw new BadRequestException("URL inválida");
    }
    
    return LinkLister.getLinksV2(url);
  }
  
  private boolean isValidUrl(String url) {
    // Implementar a lógica de validação da URL aqui
    // Por exemplo, você pode utilizar uma biblioteca externa ou expressões regulares
    
    // Retorna true se a URL for válida, caso contrário, retorna false
  }
}