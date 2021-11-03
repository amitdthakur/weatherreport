CONTENTS OF THIS FILE
---------------------

* Introduction
* Build



INTRODUCTION
------------


Build and Run on local
------------
Commands to build application

1. Clone the project from the github.
2. Install my sql and run dataBaseScripts.sql file and add correct values of url,userName and password in application.yml.
3. Run mvn clean install command to download all dependencies.This will resolve all dependencies issue.1
3. To build docker image docker image build -t docker image build -t amitdthakur09/weatherreport .  .
4. To run container docker container run -d -p 8080:8080 amitdthakur09/weatherreport:0.0.1


