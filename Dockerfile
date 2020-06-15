FROM openjdk:8-jre-slim
WORKDIR /home
COPY /target/UserManagement-1.0.jar UserManagement.jar
ENTRYPOINT ["java", "-jar", "UserManagement.jar"]