package de.gedoplan.demo.rest;

import de.gedoplan.demo.service.ServerInfoService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;

@Path("hello")
@ApplicationScoped
public class HelloResource {

  @Inject
  ServerInfoService serverInfoService;

  @Context
  HttpServletRequest servletRequest;

  @Context
  HttpHeaders httpHeaders;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String getHello(@QueryParam("verbose") boolean verbose) {
    var baseMessage = "\"Hello World!\" from " + this.serverInfoService.getDescription();

    String gedoplanInfo = "";
    var gedoplanName = System.getProperty("gedoplan.name");
    if (gedoplanName != null) {
      gedoplanInfo = "\n  powered by " + gedoplanName;
    }

    String verboseInfo = "";
    if (verbose) {
      verboseInfo = "\n  called from " + this.servletRequest.getRemoteHost()
          + " (" + this.servletRequest.getRemoteAddr() + ")";
      var requestHeaders = this.httpHeaders.getRequestHeaders();
      for (var entry : requestHeaders.entrySet()) {
        verboseInfo += "\n  " + entry.getKey() + "=" + entry.getValue();
      }
    }

    return baseMessage + gedoplanInfo + verboseInfo + "\n";
  }
}
