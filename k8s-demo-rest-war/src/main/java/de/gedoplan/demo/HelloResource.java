package de.gedoplan.demo;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("hello")
@ApplicationScoped
public class HelloResource {

  @Inject
  ServerInfoService serverInfoService;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String getHello() {
    return "\"Hello World!\" from " + this.serverInfoService.getDescription() + " powered by " + System.getProperty("gedoplan.name");
  }
}
