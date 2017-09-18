# weather-agiletrailbrazers

### To download the source code
```sh
git clone https://github.com/sousagh/weather-agiletrailbrazers.git
```


### To build the application
```sh
cd weather-agiletrailbrazers/
mvn package
```

### To run the application
```sh
java -jar target/weather-0.0.1-SNAPSHOT.jar
```
### To check the API documentation

http://localhost:8080/swagger-ui.html

### Test data

When the application runs, it adds the following test data into the database:

* London - latitude 51.51, longitude -0.13
* Rio de Janeiro  - latitude -22.9, longitude -43.21
* Beijin - latitude 39.91, longitude 116.4

Example of successful request:

http://localhost:8080/tempDiff?lat1=51.51&long1=-0.13&lat2=-22.9&long2=-43.21
