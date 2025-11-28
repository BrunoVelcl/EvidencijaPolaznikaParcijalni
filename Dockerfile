FROM eclipse-temurin:24-jre
WORKDIR app/
COPY /target/evidencijapolaznika-0.0.1-SNAPSHOT.jar /app/evidencijapolaznika-0.0.1-SNAPSHOT.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","evidencijapolaznika-0.0.1-SNAPSHOT.jar"]