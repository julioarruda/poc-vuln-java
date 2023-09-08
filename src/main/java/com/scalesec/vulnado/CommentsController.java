-----------------------
                                                 

                                                class CommentRequest implements Serializable {
                                                  public String username;
                                                  public String body;
                                                }

                                                @ResponseStatus(HttpStatus.BAD_REQUEST)
                                                class BadRequest extends RuntimeException {
                                                  public BadRequest(String exception) {
                                                    super(exception);
                                                  }
                                                }

                                                @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                                                class ServerError extends RuntimeException {
                                                  public ServerError(String exception) {
                                                    super(exception);
                                                  }
                                                }
                                                