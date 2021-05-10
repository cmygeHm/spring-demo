#!/bin/sh

mvn -N io.takari:maven:wrapper

./mvnw clean install

./mvnw spring-boot:run
