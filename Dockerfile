# Usar uma imagem base do Maven com JDK
FROM maven:3.8.4-openjdk-17-slim AS build

# Definir o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiar o arquivo de configuração do Maven
COPY pom.xml .

# Baixar as dependências do projeto (isso é armazenado em cache para builds futuros)
RUN mvn dependency:go-offline

# Copiar o restante do projeto para o contêiner
COPY src/ ./src/

# Compilar o projeto e gerar o JAR
RUN mvn package -DskipTests

# Usar uma imagem leve do JDK para a execução
FROM openjdk:17-jdk-slim

# Definir o diretório de trabalho
WORKDIR /app
