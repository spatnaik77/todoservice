FROM java:8
VOLUME /tmp
EXPOSE 8080
ADD target/todo-0.0.1-SNAPSHOT.jar target/app.jar
ENTRYPOINT ["java", "-jar", "target/app.jar"]
