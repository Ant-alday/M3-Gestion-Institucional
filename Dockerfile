# Imagen base: Java 21 en Alpine (versión ligera de Linux)
FROM eclipse-temurin:21-jre-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el .jar generado por Maven
COPY target/*.jar app.jar

# Puerto del microservicio — cambiar según cada MS
EXPOSE 8083

# Comando de arranque
ENTRYPOINT ["java", "-jar", "app.jar"]
