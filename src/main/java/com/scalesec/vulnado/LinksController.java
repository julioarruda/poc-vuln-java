

      @RequestMapping(value = "/links-v2", produces = "application/json")
      List<String> linksV2(@RequestParam String url) throws BadRequest{
      String sanitizedUrl = Encoder.forHtmlContent(url); // Sanitiza o parâmetro "url"
      
      return LinkLister.getLinksV2(sanitizedUrl);
    }

                                                
                                                