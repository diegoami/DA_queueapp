# BUILD

docker build -t queapp-frontend --file Dockerfile . 
 
# RUN

docker run --rm -p 4200:4200 --net host queapp-frontend:latest
