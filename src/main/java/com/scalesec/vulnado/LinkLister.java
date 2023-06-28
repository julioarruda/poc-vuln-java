package com.scalesec.vulnado;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.net.*;

public class LinkLister {
  public static List<String> getLinks(String url) throws IOException {
    List<String> result = new ArrayList<String>();
    Document doc = Jsoup.connect(url).get();
    Elements links = doc.select("a");
    for (Element link : links) {
      result.add(link.absUrl("href"));
    }
    return result;
  }
  
  private static boolean isPrivateIP(String host) {
    String[] ipParts = host.split("\\.");
    int firstPart = Integer.parseInt(ipParts[0]);
    int secondPart = Integer.parseInt(ipParts[1]);
    
    if (firstPart == 10) {
      return true;
    }
    
    if (firstPart == 192 && secondPart == 168) {
      return true;
    }
    
    if (firstPart == 172 && secondPart >= 16 && secondPart <= 31) {
      return true;
    }
    
    return false;
  }

  public static List<String> getLinksV2(String url) throws BadRequest {
    try {
      URL aUrl= new URL(url);
      String host = aUrl.getHost();
      System.out.println(host);
      if (isPrivateIP(host)){
        throw new BadRequest("Use of Private IP");
      } else {
        return getLinks(url);
      }
    } catch(Exception e) {
      throw new BadRequest(e.getMessage());
    }
  }
}