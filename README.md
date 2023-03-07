# Airway flight planning API.

Flight Management REST API project that includes input validation, synchronized operations, central exception handling, and logging features for improved functionality and reliability.

## Features

- FLIGHT
  - Creating And Updating Flight
    - If the id is sent in the request, the flight belonging to that id is updated, if the id is not given, a new flight is 	created with the information given.
    - Create and Update operations processes synchronized
    -  business rules
      - 1. There must be daily at most 3 flights for an airline between 2 destinations.
      - 2. If the plane has a flight between the given dates, it cannot create a new flight
  - List all flights
  - Filtering flights by arrival and departure airport
  - Deleting flight by id
- AIRPORT
  - Filtering airports by airport code
  - List all airports
- AIRPLANE
  - List all airplanes
  - Filtering airplanes by airplane and airline codes
- AIRLINE
  - List all airlines
  - Filtering airlines by airline code


## Technologies used

1. Java (Programming Language)
2. Spring Boot (Application Platform)
3. Spring Data JPA (Data persistence)
4. H2 (Database)
5. Swagger (Documentation)

## Getting Started

The source code can be checked out to your local and then build and run the application either from your IDE after importing to it as a maven project, or just from a command line. Follow these steps for the command-line option:  

### Prerequisites
1. Java 17
2. Maven 4
3. Git

### Installing & Running

#### Clone this repo into your local: 
	
```
git clone https://github.com/hknyildz/FlightsApi.git
```

####  Build using maven 
	
```
mvn clean install
```
	
#### Start the app
	
```
mvn spring-boot:run
```
	
#### Access the Home screen

The application will be available at the URL: [Home](http://localhost:8080).

The home screen will give you relevant links to navigate, including the API end-points.
	
#### Test the URLs
	
    1. [http://localhost:8080/flights?departure=GRU]
    2. [http://localhost:8080/airplanes?airline=AAL]
    
## API Documentation
API documentation can be accessed via [Swagger UI](http://localhost:8080/swagger-ui.html) 
Also postman collection can be imported from (src/main/resources/FlightApı.postman_collection.json)

## Database

This application is using H2 embedded database, which (database as well as data) will be removed from memory when the application goes down.

While the application is running, you can access the [H2 Console](http://localhost:8080/h2-console) if you want to see the data outside the application. 

You can connect to the DB using the JDBC URL: 'jdbc:h2:file:./data/fileDb' and user 'sa' with NO password. 


## Data pre-loading

Sample data is pre-loaded from data.sql file(src/main/resources/data.sql).
