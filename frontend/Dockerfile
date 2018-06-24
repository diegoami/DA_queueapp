FROM node:9-alpine as builder

WORKDIR /app

COPY package.json /app
RUN npm install
COPY . /app


CMD  [ "npm", "start" ]
