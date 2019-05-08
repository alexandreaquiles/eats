#!/usr/bin/env bash

docker network create eats-network

docker pull mongo:3.6
sudo service mongod stop
docker run --rm -d -p 27017:27017 --network eats-network --name eats.mongo mongo:3.6

docker pull mysql:5.7
sudo service mysqld stop
docker run --rm -d -p 3306:3306 --network eats-network --name eats.mysql -e MYSQL_ROOT_PASSWORD=caelum123 mysql:5.7

cd eats-microservice

cd service-registry
mvn clean install
docker build -t eats/service-registry .
docker run --rm -d -p 8761:8761 --network eats-network --name eats.service.registry eats/service-registry


# docker network inspect eats-network 
# ...

# docker ps
#CONTAINER ID        IMAGE                   COMMAND                  CREATED             STATUS              PORTS                    NAMES
#2527c10df623        eats/service-registry   "/bin/sh -c 'java ..."   15 seconds ago      Up 13 seconds       0.0.0.0:8761->8761/tcp   tender_swirles

# docker logs 2527c10df623
# docker stop 2527c10df623

#----------------

cd eats-distancia
mvn clean install
docker build -t eats/distancia eats-distancia/.
docker run --rm -d -p 8081:8081 --network eats-network --name eats.distancia -e SPRING_DATA_MONGODB_HOST=eats.mongo -e EUREKA_URI=http://eats.service.registry:8761/eureka eats/distancia



# http://localhost:8081/restaurantes/71503510/restaurante
# 404


# http://localhost:8081/restaurantes/mais-proximos/71503510
# 200
# []

cd eats-pagamento
mvn clean install
docker build -t eats/pagamento .
docker run --rm -d  -p 8082:8082 --network eats-network --name eats.pagamento -e SPRING_DATASOURCE_URL=jdbc:mysql://eats.mysql/eats_pagamento?createDatabaseIfNotExist=true -e SPRING_DATASOURCE_USERNAME=root -e SPRING_DATASOURCE_PASSWORD=caelum123 -e EUREKA_URI=http://eats.service.registry:8761/eureka eats/pagamento

cd eats
mvn clean install
cd eats-application
docker build -t eats/monolito .
docker run --rm -d -p 8080:8080 --network eats-network --name eats.monolito -e SPRING_DATASOURCE_URL=jdbc:mysql://eats.mysql/eats?createDatabaseIfNotExist=true -e SPRING_DATASOURCE_USERNAME=root -e SPRING_DATASOURCE_PASSWORD=caelum123 -e EUREKA_URI=http://eats.service.registry:8761/eureka eats/monolito

cd api-gateway
mvn clean install
docker build -t eats/api-gateway
docker build -t eats/monolito .
docker run --rm -d -p 9999:9999 --network eats-network --name eats.api.gateway -e EUREKA_URI=http://eats.service.registry:8761/eureka eats/api-gateway

cd ../../eats-ui
ng build --prod
docker build -t eats/ui .
docker run --rm -d -p 80:80 --network eats-network --name eats.ui eats/ui
 

