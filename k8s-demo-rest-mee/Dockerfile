#FROM openjdk:8
FROM gcr.io/distroless/java

ADD maven/* /opt/

ENTRYPOINT [ "java", "-cp", "/opt/*", "de.gedoplan.demo.Main" ]

EXPOSE 8080
