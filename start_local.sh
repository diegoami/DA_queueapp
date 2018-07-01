pushd
prepare_backend.sh
popd

pushd backend
java --add-modules java.xml.bind -jar target/omini-app-0.0.1-spring-boot.jar &
popd

pushd frontend
npm install
npm start
popd 

