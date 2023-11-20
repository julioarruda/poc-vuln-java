FROM nginx:alpine
RUN addgroup -g 1001 -S nonroot && adduser -u 1001 -S nonroot -G nonroot  # Incluido por GFT AI Impact Bot
USER nonroot  # Incluido por GFT AI Impact Bot
COPY . /usr/share/nginx/html
.