# Taxi Service - Course Project
## Summary
This is a simple API for the Taxi service.
This project - is an implementation REST API for creating, retrieving, editing and deleting objects by using REST requests: POST, PUT, DELETE, GET.
## Requirements
- Java version 15.0.2 or higher
- Apache Maven version 3.8.6 or higher.
## Build and Run
### Compile and run a Taxi service application:
- unpack archive to any folder on PC.
- start the command line(cmd.exe), PowerShell or terminal(linux).
- make sure that the required versions of Java and Maven are installed, (run commands: "#java -version", "#mvn -version").
- go to root folder of project(that contains pom.xml): "#cd {Path_to_project}/TaxiSercice"
#### Simple run:
- just run command: "mvn org.springframework.boot:spring-boot-maven-plugin:run"
#### Build and run a jar file:
- Run command: "#mvn clean package" - it will build target/TaxiService-1.0-SNAPSHOT.jar file
- Copy the TaxiService-1.0-SNAPSHOT.jar to any place or anoter PC.
- Copy the "Data" folder to folder near with TaxiService-1.0-SNAPSHOT.jar file
- Run via cmd: "#java -jar TaxiService-1.0-SNAPSHOT.jar"

The API will be accessible by http://localhost:8080

## Detailed
For sending REST requests is easy to use the Postman app.
##### Get all entities of this type(GET):
- getAll() - returns all entities corresponding to this service
    * http://localhost:8080/car/getAll
    * http://localhost:8080/order/getAll
    * http://localhost:8080/driver/getAll
    * http://localhost:8080/client/getAll

##### Get the object of a certain type by id(GET):
- getById(int id) - returns entity by id
    * http://localhost:8080/car/getById?id=1
    * http://localhost:8080/order/getById?id=2
    * http://localhost:8080/driver/getById?id=3
    * http://localhost:8080/client/getById?id=1

##### Create the object of this type(POST):
- add() - creates new entity/add
    * http://localhost:8080/car/add
    * http://localhost:8080/order/add
    * http://localhost:8080/driver/add
    * http://localhost:8080/client/add

##### Edit the object of this type by id(PUT):
- edit(int id, TaxiEntity entity) - rewrite entity by id (is using for editing)
    * http://localhost:8080/car/edit/{id}
    * http://localhost:8080/order/edit/{id}
    * http://localhost:8080/driver/edit/{id}
    * http://localhost:8080/client/edit/{id}

##### Delete the object of this type by id(DELETE):
- removeById(int id) - видаляє сутність по id.
    *  http://localhost:8080/car/{id}
    *  http://localhost:8080/order/{id}
    *  http://localhost:8080/driver/{id}
    *  http://localhost:8080/client/{id}

#### Also the most of functionality can be performed using browser only and GET requests(except editing):
- Get all entities and show they in browser as formated string:
    *  http://localhost:8080/car/getStringAll
    *  http://localhost:8080/order/getStringAll
    *  http://localhost:8080/driver/getStringAll
    *  http://localhost:8080/client/getStringAll
- Get entity by Id and display it as formatted string:
    *  http://localhost:8080/car/getById?id=0
    *  http://localhost:8080/order/getStringById?id=12
    *  http://localhost:8080/driver/getStringById?id=4
    *  http://localhost:8080/client/getStringById?id=10
- Create an entity and display it as formatted string:
    *  http://localhost:8080/car/add?name=car_test&vinCode=VIN111&stateNumber=BC66-64test&driverId=1&description=HB
    *  http://localhost:8080/order/add?name=Order_test&carId=4&clientId=12&distance=13.0&price=120.0
    *  http://localhost:8080/driver/add?name=Bogdan_test-2&phone=380665544321&experience=2%20years%205%20monts
    *  http://localhost:8080/client/add?name=Vova_test&phone=380111111112
- Remove entity by id:
    * http://localhost:8080/car/remove?id=2
    * http://localhost:8080/order/remove?id=1
    * http://localhost:8080/driver/remove?id=1
    * http://localhost:8080/client/remove?id=1