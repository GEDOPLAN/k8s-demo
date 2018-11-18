FROM openjdk:8

ADD target/k8s-demo-hello.jar /opt/
#ADD target/${project.build.finalName}.jar /opt/
#ADD maven/* /opt/

CMD [ "java", "-jar", "/opt/k8s-demo-hello.jar" ]
#CMD [ "java", "-jar", "/opt/${project.build.finalName}.jar" ]


