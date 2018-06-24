FROM openjdk:8-jdk-alpine


RUN mkdir -p target


COPY target target
WORKDIR target
EXPOSE 9095

CMD ["java",  "-jar", "omini-app-0.0.1-spring-boot.jar"]
