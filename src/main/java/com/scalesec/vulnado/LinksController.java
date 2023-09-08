

@EnableAutoConfiguration
@RestController
@RequestMapping("/links")
public class LinksController {
  
  @Autowired
  private LinkLister linkLister;
  
  @GetMapping(produces = "application/json")
  public List<String> getLinks(@RequestParam String url) throws IOException {
    return linkLister.getLinks(url);
  }
  
  @PostMapping(produces = "application/json")
  public List<String> addLink(@RequestParam String url) throws IOException {
    return linkLister.addLink(url);
  }
  
  @PutMapping(produces = "application/json")
  public void updateLink(@RequestParam String url) throws IOException {
    linkLister.updateLink(url);
  }
  
  @DeleteMapping(produces = "application/json")
  public void deleteLink(@RequestParam String url) throws IOException {
    linkLister.deleteLink(url);
  }
}