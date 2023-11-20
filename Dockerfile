# Dockerfile
# Incluido por GFT AI Impact Bot
# Use a imagem base do OpenJDK 8
FROM openjdk:8

# Incluido por GFT AI Impact Bot
# Crie um usuário não root para executar a aplicação
RUN useradd -ms /bin/bash newuser

# Incluido por GFT AI Impact Bot
# Mude para o usuário não root
USER newuser

# Incluido por GFT AI Impact Bot
# Atualize os pacotes e instale as dependências necessárias
RUN apt-get update && \
    apt-get install -y --no-install-recommends build-essential maven default-jdk cowsay netcat && \
    rm -rf /var/lib/apt/lists/* && \
    update-alternatives --config javac

# Incluido por GFT AI Impact Bot
# Copie os arquivos do projeto para o diretório de trabalho
COPY --chown=newuser:newuser . .

# Incluido por GFT AI Impact Bot
# Execute o comando para iniciar a aplicação
CMD ["mvn", "spring-boot:run"]