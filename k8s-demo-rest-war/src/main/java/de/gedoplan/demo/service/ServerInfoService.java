package de.gedoplan.demo.service;

import java.net.InetAddress;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import lombok.Getter;

@ApplicationScoped
public class ServerInfoService {

  @Getter
  private String description;

  @PostConstruct
  void postConstruct() {
    InetAddress inetAddress = null;
    try {
      inetAddress = InetAddress.getLocalHost();
    } catch (Exception e) {
      // ignore
    }

    if (System.getProperty("com.sun.aas.hostName") != null) {
      String[] instanceRoot = System.getProperty("com.sun.aas.instanceRoot").split("/\\\\");
      this.description = String.format("GLF@%s (domain=%s, instance=%s", inetAddress, instanceRoot[instanceRoot.length - 2], System.getProperty("com.sun.aas.instanceName"));
    }

    else if (System.getProperty("jboss.server.name") != null) {
      this.description = String.format("WFLY@%s (base=%s, name=%s)", inetAddress, System.getProperty("jboss.server.base.dir"), System.getProperty("jboss.server.name"));
    }

    else if (System.getProperty("wlp.server.name") != null) {
      this.description = String.format("WLP@%s (home=%s, name=%s)", inetAddress, System.getProperty("wlp.install.dir"), System.getProperty("wlp.server.name"));
    }

    else if (System.getProperty("weblogic.Name") != null) {
      this.description = String.format("WLS@%s (name=%s)", inetAddress, System.getProperty("weblogic.Name"));
    }

    else {
      this.description = String.format("unknownServer@%s", inetAddress);
    }

  }
}
