#!/bin/bash
service postgresql start
sleep 5
pushd backend
java -jar target/omini-app-0.0.1-spring-boot.jar &
popd
sleep 5
pushd frontend
ng serve --host 0.0.0.0
popd
sleep 5
wget http://localhost:4200
