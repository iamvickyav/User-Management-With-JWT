FROM openjdk:8-jre-slim
WORKDIR /home
COPY /target/UserManagement-1.0.jar UserManagement.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "UserManagement.jar"]