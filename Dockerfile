FROM openjdk:11
ARG JAR_FILE=./practice-api/build/libs/*.jar
COPY ${JAR_FILE} /deploy/jar/app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-Dserver.port=8083", "-jar", "/deploy/jar/app.jar"]