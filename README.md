# LOCAL

Assuming you installed java8, nodejs, postgres

    scripts/prepare_backend.sh

    pushd backend
    java --add-modules java.xml.bind -jar target/omini-app-0.0.1-spring-boot.jar &
    popd

    pushd frontend
    npm install
    npm start
    popd 
    


# DOCKER


# CLEAN UP

Make sure that there are no stale images

    docker system prune -a

or docker processes running

    docker ps

# BUILD

    docker build -t queapp-all --file Dockerfile .

# RUN

    docker run -p 4200:4200 queapp-all:latest 

# CONNECT

http://localhost:4200


