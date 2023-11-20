# Dockerfile
FROM nginx:alpine
RUN addgroup -g 1001 -S nginx && \  # Incluido por GFT AI Impact Bot
    adduser -u 1001 -D -S -G nginx nginx  # Incluido por GFT AI Impact Bot
USER 1001  # Incluido por GFT AI Impact Bot
COPY . /usr/share/nginx/html
.