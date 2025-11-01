FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/quote-service-0.0.1-SNAPSHOT.jar app.jar
RUN apt-get update && apt-get install -y curl \
    && curl -o wait-for-it.sh https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh \
    && chmod +x wait-for-it.sh \
    && apt-get clean && rm -rf /var/lib/apt/lists/*
EXPOSE 8082
ENTRYPOINT ["./wait-for-it.sh", "mysql:3306", "--", "./wait-for-it.sh", "eureka-server:8761", "--", "java", "-jar", "app.jar"]