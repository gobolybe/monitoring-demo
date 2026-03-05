# 1. fázis: Build (Node.js)
FROM node:20-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build --configuration=production

# 2. fázis: Web szerver (Nginx)
FROM nginx:alpine

# A saját nginx konfigurációnk bemásolása
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Töröljük az alapértelmezett tartalmat
RUN rm -rf /usr/share/nginx/html/*

# Másoljuk a build eredményét
# Megjegyzés: Ellenőrizd a dist mappa pontos útvonalát az angular.json-ban!
COPY --from=build /app/dist/usermaintenance-gui/browser /usr/share/nginx/html

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
