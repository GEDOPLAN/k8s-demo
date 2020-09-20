FROM jboss/wildfly:20.0.0.Final

# Add deployment
ADD --chown=jboss:jboss maven/* /opt/jboss/wildfly/standalone/deployments/

USER jboss

# Setup WildFly
ADD --chown=jboss:jboss src/main/wildfly/wildfly-setup.cli $HOME
RUN cd $HOME \
    && sh $JBOSS_HOME/bin/jboss-cli.sh --file=wildfly-setup.cli \
    && rm wildfly-setup.cli \
    && rm -fr $JBOSS_HOME/standalone/configuration/standalone_xml_history
   
# Start WildFly
ENTRYPOINT ["/opt/jboss/wildfly/bin/standalone.sh", "-c", "standalone-full.xml", "-b", "0.0.0.0"]