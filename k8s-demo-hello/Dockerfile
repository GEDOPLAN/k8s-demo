FROM gcr.io/distroless/java17

ADD target/k8s-demo-hello.jar /opt/
#ADD target/${project.build.finalName}.jar /opt/
#ADD maven/* /opt/

ENTRYPOINT [ "java", "-jar", "/opt/k8s-demo-hello.jar" ]
#ENTRYPOINT [ "java", "-jar", "/opt/${project.build.finalName}.jar" ]


