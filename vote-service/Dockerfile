FROM maven:3.9.9-eclipse-temurin-21-jammy AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

RUN apt-get update && apt-get install -y curl \
    && curl -o wait-for-it.sh https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh \
    && chmod +x wait-for-it.sh \
    && apt-get clean && rm -rf /var/lib/apt/lists/*
EXPOSE 8083
ENTRYPOINT ["./wait-for-it.sh", "mysql:3306", "--", "./wait-for-it.sh", "eureka-server:8761", "--", "java", "-jar", "app.jar"]