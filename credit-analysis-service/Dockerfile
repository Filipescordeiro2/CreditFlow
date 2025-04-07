# Usando a imagem oficial do OpenJDK 21 (compatível com class version 65.0)
FROM openjdk:21-jdk-slim

# Define o diretório de trabalho no container
WORKDIR /app

# Copia o JAR do microsserviço para dentro do container
COPY target/CreditAnalysis-0.0.1-SNAPSHOT.jar app.jar

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
