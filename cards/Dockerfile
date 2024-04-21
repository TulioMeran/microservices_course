FROM gradle:jdk17 AS build
WORKDIR /home/app
COPY . /home/app
RUN ./gradlew build

FROM openjdk:17.0-slim
EXPOSE 8082
COPY --from=build /home/app/build/libs/*.jar app.jar
ENTRYPOINT ["sh","-c","java -jar /app.jar"]