#!/bin/bash
pushd scripts
./prepare_backend.sh
popd

pushd backend
java -jar target/omini-app-0.0.1-spring-boot.jar &
popd

pushd frontend
npm install
npm start
popd 

