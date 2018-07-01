# CHECK PORT IS NOT BUSY

netstat -tuplen | grep 5432

# DOCKER BUILD

docker build -t queapp-db .

# DOCKER RUN 
 
docker run --rm -p 5432:5432 queapp-db
