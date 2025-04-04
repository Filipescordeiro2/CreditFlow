# Usando a imagem oficial do OpenJDK 17
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho no container
WORKDIR /app

# Copia o JAR do microsserviço para dentro do container
COPY target/credit-analysis-service.jar app.jar

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
