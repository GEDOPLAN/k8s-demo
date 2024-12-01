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
import jakarta.ws.rs.core.MultivaluedMap;

@Path("hello")
@ApplicationScoped
public class HelloResource {

  @Inject
  ServerInfoService serverInfoService;

  @Context
  HttpServletRequest servletRequest;

  @Context
  private HttpHeaders httpHeaders;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String getHello(@QueryParam("verbose") boolean verbose) {
    StringBuilder sb = new StringBuilder();

    sb.append("\"Hello World!\" from " + this.serverInfoService.getDescription());
    String gedoplanName = System.getProperty("gedoplan.name");
    if (gedoplanName != null) {
      sb.append("\n  powered by " + gedoplanName);
    }

    if (verbose) {
      sb.append("\n  called from " + this.servletRequest.getRemoteHost() + " (" + this.servletRequest.getRemoteAddr() + ")");
      MultivaluedMap<String, String> requestHeaders = this.httpHeaders.getRequestHeaders();
      for (Entry<String, List<String>> entry : requestHeaders.entrySet()) {
        sb.append("\n  " + entry.getKey() + "=" + entry.getValue());
      }
    }

    sb.append("\n");
    return sb.toString();
  }
}
