#!/bin/bash
cd wallet-user      && ./mvnw --quiet clean compile package     && cd .. &&
cd wallet-timeline  && ./mvnw --quiet clean compile package     && cd .. &&
cd bill-payment     && ./mvnw --quiet clean compile package     && cd .. &&
docker-compose up -d --remove-orphans