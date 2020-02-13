package de.gedoplan.demo.rest;

import de.gedoplan.demo.service.ServerInfoService;

import java.util.List;
import java.util.Map.Entry;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

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
    sb.append("\n  powered by " + System.getProperty("gedoplan.name"));

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
