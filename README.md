# RUN IMAGE

## START    

    docker run -p 4200:4200 -p 9095:9095 --name queapp-container diegoami/queapp:latest

## STOP
   
    docker stop queapp-container

## REMOVE
  
    docker rm queapp-container

# LOCAL

Assuming you installed java8, nodejs, postgres

    ./start_local.sh

To stop the backend

    kill -9 $(ps -aef | grep omini-app-0.0.1-spring-boot.jar | awk '{ print $2 }')

# DOCKER


## CLEAN UP

Make sure that there are no stale images

    docker system prune -a

or docker processes running

    docker ps

or the ports are not taken

   nestat -tulp | grep 4200
   nestat -tulp | grep 9095


## BUILD

    docker build -t queapp-all --file Dockerfile .

## RUN

    docker run -p 4200:4200 -p 9095:9095 --name queapp-all-container queapp-all:latest

## STOP

    docker stop queapp-all-container

## REMOVE

    docker rm queapp-all-container

# CONNECT

http://localhost:4200



