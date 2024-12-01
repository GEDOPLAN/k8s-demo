FROM gcr.io/distroless/java17

ADD target/k8s-demo-hello.jar /opt/

ENTRYPOINT [ "java", "-jar", "/opt/k8s-demo-hello.jar" ]

