FROM arm64v8/openjdk:17
EXPOSE 8081
WORKDIR /project
COPY ./target/spring-boot-1.0-SNAPSHOT.jar ./
CMD [ "java", "-jar", "spring-boot-1.0-SNAPSHOT.jar"]
