FROM openjdk:8-jdk-alpine


RUN mkdir -p target


COPY target target
COPY wait-for-postgres.sh target
WORKDIR target
EXPOSE 9095

CMD ["./wait-for-postgres.sh", "localhost", "java",  "-jar", "omini-app-0.0.1-spring-boot.jar"]
