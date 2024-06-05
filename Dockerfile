FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the application using OpenJDK
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/MyBotApplication.jar /app/MyBotApplication.jar
COPY src/main/resources/application.properties /app/application.properties
ENTRYPOINT ["java", "-jar", "/app/mybotapplication.jar"]