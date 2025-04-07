# Usando a imagem oficial do OpenJDK 21 (compatível com class version 65.0)
FROM openjdk:21-jdk-slim

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o JAR gerado pelo Maven para o container
COPY target/OrderAnalyze-0.0.1-SNAPSHOT.jar app.jar

# Comando para executar a aplicação Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
