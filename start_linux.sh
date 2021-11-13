#!/bin/bash
find ./ -type f -name "pom.xml" | while read line;
  do
    echo found file at $line
    len=${#line}
    pomLen=$((len-8))
    pomDir=${line:0:pomLen}
    cd $pomDir
    ./mvnw clean compile package
  done;
