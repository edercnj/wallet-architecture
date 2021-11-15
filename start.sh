#!/bin/bash
cd wallet-user && ./mvnw clean compile package && cd .. &&
docker-compose down && 
docker-compose up -d --remove-orphans