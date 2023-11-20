# Dockerfile
FROM nginx:alpine
RUN addgroup -g 1000 -S nginx && \  # Incluido por GFT AI Impact Bot
    adduser -u 1000 -D -S -G nginx nginx  # Incluido por GFT AI Impact Bot
USER nginx  # Incluido por GFT AI Impact Bot
COPY . /usr/share/nginx/html
.