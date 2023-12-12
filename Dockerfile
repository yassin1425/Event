FROM openjdk:11-jdk-slim
ADD target/EventProject-1.0.jar event.jar
ENTRYPOINT ["java", "-jar","event.jar"]
EXPOSE 8086