#Table creation queries:
CREATE TABLE sensorMetrics (
    sensorId int,
    temperature float,
    humidity int,
    windSpeed float,
    pressure float,
    lastUpdated TIMESTAMP
);

CREATE TABLE sensorMetaData (
    sensorId int,
    countryName varchar(255),
    cityName varchar(255),
    created TIMESTAMP
);

#Primary key:
ALTER TABLE sensorMetaData ADD PRIMARY KEY(sensorId);