# INTRODUCTION

Three methods to set up the project:

- Retrieve the image from dockerhub
- Build a docker image locally
- Set up the required software on your OS

# CONNECT

The web application in all cases is accessible at 

http://localhost:4200

# RUN IMAGE

## START    

    docker run -p 4200:4200 -p 9095:9095 --name queapp-container diegoami/queapp:latest

## STOP
   
    docker stop queapp-container

## REMOVE
  
    docker rm queapp-container
    
# DOCKER


## CLEAN UP

Make sure that there are no stale images

    docker system prune -a

or docker processes running

    docker ps

or the ports are not taken

    netstat -tulp | grep 4200
    netstat -tulp | grep 9095

Remove directories having wrong privileges

    sudo rm -rf postgres-data

## BUILD

    docker build -t queapp-all --file Dockerfile .

## RUN

    docker run -p 4200:4200 -p 9095:9095 --name queapp-all-container queapp-all:latest

## STOP

    docker stop queapp-all-container

## REMOVE

    docker rm queapp-all-container


# LOCAL

## INSTALL

Use Dockerfile as a reference to check what software to install

## START

Assuming you installed java8, nodejs, postgres -- Execute

    ./start_local.sh

## STOP

To stop the backend

    kill -9 $(ps -aef | grep omini-app-0.0.1-spring-boot.jar | awk '{ print $2 }')




