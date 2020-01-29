FROM jboss/wildfly:11.0.0.Final

ADD build/libs/admin.war /opt/jboss/wildfly/standalone/deployments/

ADD src/main/resources/docker/wildfly-11.0.0.Final-weld-3.0.1.Final-patch.zip /opt/jboss/patch/

RUN /opt/jboss/wildfly/bin/jboss-cli.sh --command="patch apply /opt/jboss/patch/wildfly-11.0.0.Final-weld-3.0.1.Final-patch.zip"

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]