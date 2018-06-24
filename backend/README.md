# BUILD JAR

mvn clean compile package

# BUILD DOCKER

docker build -t queapp-backend --file Dockerfile . 

# RUN

docker run --rm -p 9095:9095 --net host queapp-backend:latest