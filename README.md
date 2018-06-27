# CLEAN UP

Make sure that there are no stale images

    docker system prune -a

or docker processes running

    docker ps




It may be necessary to remove the directory postgres-data

    sudo rm -rf ./postgres-data
    

# BUILD

    docker-compose build

# RUN

docker-compose up may not work because app does not wait for postgres (should add a waiting script). Therefore start the services one after the other.

    docker-compose run -d postgres
    docker-compose run -d app
    docker-compose run -d web

after that
  

    docker-compose down


# CONNECT

http://localhost:4200


