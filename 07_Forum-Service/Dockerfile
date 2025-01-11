FROM eclipse-temurin:21.0.5_11-jre-alpine

WORKDIR /app

COPY ./target/07_Forum-Service-0.0.1-SNAPSHOT.jar ./forum-service.jar

ENTRYPOINT ["java","-jar","/app/forum-service.jar"]