FROM java:8
VOLUME /tmp
EXPOSE 8080
ADD target/todo-1.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
