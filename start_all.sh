service postgresql start
sleep 1
pushd backend
java -jar target/omini-app-0.0.1-spring-boot.jar &
popd
pushd frontend
npm run ng serve
popd
