FROM ubuntu:16.04
RUN apt-get update -y

RUN apt-get update -y
RUN apt-get install -y default-jre-headless
RUN apt-get install -y postgresql postgresql-client-common postgresql-client
RUN apt-get install -y apt-utils sudo
RUN mkdir -p app



COPY target app
COPY scripts app

WORKDIR app
RUN chmod u+x start_backend.sh  
   
EXPOSE 9095
ENTRYPOINT ["bash", "./start_backend.sh"]


