
                                              @CrossOrigin(origins = {"http://example.com", "https://example.com"})
                                              @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
                                              LoginResponse login(@RequestBody LoginRequest input) {
                                                User user = User.fetch(input.username);
                                                if (Postgres.md5(input.password).equals(user.hashedPassword)) {
                                                  return new LoginResponse(user.token(secret));
                                                } else {
                                                  throw new Unauthorized("Access Denied");
                                                }
                                              }