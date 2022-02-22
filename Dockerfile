FROM arm64v8/openjdk
WORKDIR /application
EXPOSE 8080
COPY ./target/spring-boot-1.0-SNAPSHOT.jar /application/app.jar
CMD [ "java", "-jar", "app.jar" ]
