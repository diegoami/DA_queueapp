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

    docker-compose up



or if the database starts too soon

    docker-compose run -d postgres
    docker-compose run -d app
    docker-compose run -d web

after that
  

    docker-compose down


# CONNECT

http://localhost:4200


