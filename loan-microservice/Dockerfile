FROM maven AS builder
WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=builder /app/target/*.jar loan-app.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "loan-app.jar" ]