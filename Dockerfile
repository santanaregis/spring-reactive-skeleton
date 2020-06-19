#BUILD WITH GRADLE
FROM gradle:jdk11 as builder
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

#RUN
FROM openjdk:11-jre-slim as runner
EXPOSE 8080
COPY --from=builder /home/gradle/src/build/libs/*.jar app.jar
CMD ["java","-jar","app.jar"]


# "NORMAL" DOCKERFILE
#FROM openjdk:8
#RUN mkdir /app
#WORKDIR /app
#COPY /build/libs/*.jar /app/app.jar
#EXPOSE 8080
#CMD ["java", "-jar", "app.jar"]
