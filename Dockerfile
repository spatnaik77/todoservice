FROM java:8
VOLUME /tmp
EXPOSE 8080
ADD todo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
