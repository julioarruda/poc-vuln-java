# Dockerfile
# Incluido por GFT AI Impact Bot
# Usando um usuário não root para evitar privilégios desnecessários
FROM openjdk:8

RUN apt-get update && \
    apt-get install -y build-essential maven default-jdk cowsay netcat && \
    update-alternatives --config javac && \
    adduser --disabled-password --gecos '' appuser

# Incluido por GFT AI Impact Bot
# Mudando para o usuário não root
USER appuser

COPY . .

CMD ["mvn", "spring-boot:run"]