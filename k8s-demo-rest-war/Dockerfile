FROM quay.io/wildfly/wildfly:31.0.1.Final-jdk17

# Add deployment
COPY --chown=jboss:jboss target/k8s-demo-rest-war.war /opt/jboss/wildfly/standalone/deployments/

USER jboss

# Setup WildFly
COPY src/main/wildfly/wildfly-setup.cli /opt/jboss/

RUN cd /opt/jboss/ \
    && sh /opt/jboss/wildfly/bin/jboss-cli.sh --file=wildfly-setup.cli \
    && rm -fr /opt/jboss/wildfly/standalone/configuration/standalone_xml_history
# RUN rm -fr /opt/jboss/wildfly-setup.cli   # doesn't work, "no permission"

# Start WildFly
ENTRYPOINT ["/opt/jboss/wildfly/bin/standalone.sh", "-c", "standalone.xml", "-b", "0.0.0.0"]