# Dockerfile
# Incluido por GFT AI Impact Bot
# Use a imagem base do OpenJDK 8 como base
FROM openjdk:8

# Incluido por GFT AI Impact Bot
# Crie um usuário não root e um grupo de trabalho
RUN groupadd -r nonroot && useradd -r -g nonroot nonroot

# Incluido por GFT AI Impact Bot
# Mude para o usuário não root
USER nonroot

# Incluido por GFT AI Impact Bot
# Atualize o sistema e instale as dependências necessárias
RUN apt-get update && \
    apt-get install -y --no-install-recommends build-essential maven default-jdk cowsay netcat && \
    rm -rf /var/lib/apt/lists/* && \
    update-alternatives --config javac

# Incluido por GFT AI Impact Bot
# Copie o diretório atual para o contêiner
COPY . .

# Incluido por GFT AI Impact Bot
# Execute o comando para iniciar a aplicação
CMD ["mvn", "spring-boot:run"]