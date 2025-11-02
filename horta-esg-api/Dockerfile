FROM eclipse-temurin:17-jre-jammy

WORKDIR /app
COPY target/*.jar app.jar

EXPOSE 3306
RUN adduser --disabled-password --gecos "" appuser && chown -R appuser:appuser /app
USER appuser

ENTRYPOINT ["java","-jar","/app/app.jar"]

