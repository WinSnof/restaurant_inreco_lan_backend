FROM gradle:jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM bellsoft/liberica-openjdk-alpine:17
WORKDIR usr/share/app
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar
CMD ["java", "-jar", "app.jar"]