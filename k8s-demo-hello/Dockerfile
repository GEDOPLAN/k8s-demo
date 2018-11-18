FROM openjdk:8

ADD target/${project.build.finalName}.jar /maven/${project.build.finalName}.jar

CMD [ "java", "-cp", "/maven/${project.build.finalName}.jar", "de.gedoplan.demo.Hello" ]