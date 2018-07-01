FROM ubuntu:16.04
RUN apt-get update -y

RUN apt-get update -y
RUN apt-get install -y default-jre-headless
RUN apt-get install -y postgresql postgresql-client-common postgresql-client
RUN apt-get install -y apt-utils sudo
RUN apt-get install -y curl wget
RUN curl -sL https://deb.nodesource.com/setup_8.x | sudo -E bash -
RUN apt-get install -y nodejs 
RUN npm install npm@latest -g
RUN npm i -g @angular/cli
RUN mkdir -p app
RUN mkdir -p web

COPY backend backend
COPY scripts scripts

COPY frontend frontend


RUN chmod u+x scripts/prepare_backend.sh  
WORKDIR /scripts
RUN ./prepare_backend.sh
WORKDIR /frontend
RUN npm install
WORKDIR /

COPY start_all.sh start_all.sh
RUN chmod u+x ./start_all.sh
   
EXPOSE 4200
EXPOSE 9095
ENTRYPOINT ["bash", "./start_all.sh"]


