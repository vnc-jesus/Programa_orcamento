# Build da aplicação
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn -q -DskipTests clean package

# Imagem final
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copia o JAR gerado
COPY --from=build /app/target/*.jar app.jar

# Render usa PORT automaticamente
ENV PORT=8080

EXPOSE 8080

# Executa o JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
