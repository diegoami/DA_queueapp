pushd backend
mvn clean compile package
popd
docker-compose up
