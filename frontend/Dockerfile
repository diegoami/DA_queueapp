FROM node:9-alpine as builder

COPY package.json package-lock.json ./


RUN npm i npm@latest

RUN npm set progress=false && npm config set depth 0 && npm cache clean --force

## Storing node modules on a separate layer will prevent unnecessary npm installs at each build
RUN mkdir /ng-app
RUN cp -R ./node_modules ./ng-app

EXPOSE 4200
WORKDIR /ng-app

COPY . .

## Build the angular app in production mode and store the artifacts in dist folder
#RUN $(npm bin)/ng build --prod

CMD  [ "npm", "start" ]
