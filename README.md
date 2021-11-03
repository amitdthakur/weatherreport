# Spring Boot Kubernetes and MySQL

Weather report service developed in the spring boot with mysql database in kubernetes.

## CONTENTS OF THIS FILE

* Prerequisite
* Build and steps to run application on local.
* Docker and Kubernetes commands
* Known exceptions and resolutions
* Deployment steps on minikube
* Delete deployment, service, secret
* Next steps

## Prerequisite

- Docker with kubernetes enabled
- Kubernetes command-line tool(kubectl)
- Java 8
- Maven

## Build and steps to run application on local

Commands to build and run project on local.

1. Clone the project from the github.
2. Install MySql,run dataBaseScripts.sql file and add correct values of url,userName and password in application.yml.
3. Run mvn clean install command this will resolve all the dependencies issue.

##  Docker and Kubernetes commands

1. Run mvn clean install and build docker image docker image build -t docker image build -t amitdthakur09/weatherreport . to build docker image.
2. Push docker image to docker repo with docker push amitdthakur09/weatherreport:0.0.1

## Known exceptions and resolutions

| Exception | Status Code       | Reason | Resolution |
| ----------- | ----------- | ----------- |----------- |
| DateOlderThan30DaysException | 400 | Start date is older than current day by 30 days| Start date should never be smaller than current date by 30 days. |
| SensorAlreadyExistsException |400 | If user uses same sensor id for sensor registration | Change the sensor id|
| SensorDoesNotExistsException | 400 | If user trying to enter metrics without registering sensor | First register the sensor then add metrics|
| DataAccessException | 500 | Something went incorrect in the database | Check the loggers for the exact reason|
| RuntimeException | 500 | Generic exception | Check the loggers for the exact reason|


## Deployment steps on minikube

- Deploy configmaps and secrets.

```sh
kubectl apply -f deploy/mysql-configmap.yml
kubectl apply -f deploy/mysqldb-root-credentials.yml
kubectl apply -f deploy/mysqldb-credentials.yml
```

- Deploy deployments and services.


```sh
kubectl apply -f deploy/mysql-deployment.yml
kubectl apply -f deploy/application-deployment.yml

```
###Make sure to create tables inside the mysql database


1. kubectl exec -it {podId} /bin/bash
2. mysql -h mysql -u root -p and enter password.
3. show databases; use database; and run dataBaseScripts.sql.


- Deploy deployments and services.


```sh
kubectl apply -f deploy/application-deployment.yml

```
minikube service serviceName --url to generate the url.

- Test application with eiter curl or postman:

```curl
curl -X GET \
  http://localhost:8080/v1/sensors/metadata 
  -H 'Content-Type: application/json'
```
This will list all the sensors available in the database(sensorMetaData).

Response should be (200 status code):

```json
{
  "sensors": [
    {
      "id": 8,
      "countryName": "India",
      "cityName": "Bhandup",
      "created": "2021-11-01T22:40:15"
    }
  ]
}
```

```curl
curl -X POST \
  http://localhost:8080/v1/sensors/metadata 
  -H 'Accept: application/json' \
  -H 'Content-Type: application/json'
```
This will add sensor in the database(sensorMetaData).

Request should be:

```json
{
  "id": 2,
  "countryName": "India",
  "cityName": "Thane"
}
```

Response should be (201 status code):

```json
{
  "id": 2,
  "countryName": "India",
  "cityName": "Thane"
}
```

```curl
curl -X GET \
  http://localhost:8080/v1/sensors/metrics?sensorIds=1,3&startDate=2021-10-29&endDate=2021-10-31 
  -H 'Content-Type: application/json'
```
Parameter usage:

| Parameter Name | default Value       | Usage |
| ----------- | ----------- | ----------- |
| sensorIds   |  ALL | Sensor id which need to be searched multiple values can be passed as comma separated ALL denotes all sensor ids will be retrieved.       |
| startDate   | null |start date of the sensor id metrics report format yyyy-MM-dd        |
| endDate   |null  |end date of the sensor id metrics report format yyyy-MM-dd        |

Response should be (200 status code):

```json
{
  "sensorMetricsReports": [
    {
      "avgTemperature": 30.35,
      "avgHumidity": 23,
      "avgWindSpeed": 15.5,
      "avgPressure": 1212.5,
      "lastUpdated": "2021-10-31T14:39:38",
      "days": 2,
      "metadata": {
        "id": 1,
        "countryName": "India",
        "cityName": "Panvel",
        "created": "2021-10-31T13:21:46"
      }
    },
    {
      "avgTemperature": 32.5,
      "avgHumidity": 24,
      "avgWindSpeed": 15.5,
      "avgPressure": 1213,
      "lastUpdated": "2021-10-31T23:23:14",
      "days": 2,
      "metadata": {
        "id": 3,
        "countryName": "India",
        "cityName": "Pune",
        "created": "2021-10-31T12:55:18"
      }
    }
  ]
}
```

```curl
curl -X POST \
  http://localhost:8080/v1/sensors/metrics 
  -H 'Accept: application/json' \
  -H 'Content-Type: application/json'
```
This will add sensor in the database(sensorMetrics).

Request should be:

```json
{
  "id": 1,
  "temperature": 28.7,
  "humidity": 23,
  "windSpeed": 15.5,
  "pressure" : 1212
}
```

Response should be (201 status code):

```json
{
  "id": 1,
  "temperature": 28.7,
  "humidity": 23,
  "windSpeed": 15.5,
  "pressure" : 1212
}
```
## Delete deployment, service, secret

```sh
kubectl delete deployment deployMentName
kubectl delete service serviceName
```

## Next steps:

Improvements

1. AOP can be added for loggers.
2. Validation on request body fields.
3. Spring security.
4. BDD test cases.
5. Junit coverage improvement current coverage is 25% calculated by running mvn clean test
