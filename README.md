# Task:

Please develop a small API using the language of your choice that will enable a front end application to connect into your API and search for a list of
Rick&Morty characters by name.

The API should provide a GraphQL endpoint to enable the user to search for a list of characters,
as detailed in the documentation here:  https://rickandmortyapi.com/documentation


- The API should have a way for the user to search through the characters by name

- The characters provided as a result should follow the schema detailed in documentation

- It is not necessary to pull the data from a database. It will absolutely suffice to emulate the database by hardcoding a few results.

# All possible types of queries in the documentation

    # By filter and pagination
    query {
        characters(page: 2, filter: { name: "rick" }) {
            info {
                count
                prev
                next
                pages
            }
            results {
                name
                id
            }
        }
    
        locations(page: 2, filter: { name: "e" }) {
            info {
                count
                prev
                next
                pages
            }
            results {
                name
                id
            }
        }
        
        episodes( page:1, filter: { name: "2" }) {
            info {
                count
                prev
                next
                pages
            }
            results {
                name
                id
            }
        }
        
        # By Id
        character(id: 591) {
            id
            name
        }
        
        location(id: 80) {
            id
            name
        }
        
        episode(id: 2) {
            id
            name
        }
        
        # By Ids
        charactersByIds(ids: [591, 590]) {
            name
            id
        }
        locationsByIds(ids: [80, 81]) {
            name
            id
        }
        episodesByIds(ids: [2, 3]) {
            name
            id
        }
    }

# Development Details

This is a Spring Boot, Java 17 project. It uses:

- Java 17
- Spring Boot: Version 2.7.4
- JUnit 5: Developer-side testing
- Docker for Containerization
- Maven: build automation (Compiler Plugin 3.10.1 Resource Plugin 3.2.0)
- Intelli (later than 2022.1 version)
- GraphQL
- javafaker for fake data creation
- h2 as embedded database
- 
# Running the Application

1-  mvn clean package (Java version 17 should be supported through the available maven version)

2-(a) java -jar rickandmorty-spring-boot-graphql-api-1.0.0.jar   
            (Java version 17 should be supported)
OR

2-(b) docker build -t rickandmorty-spring-boot-graphql-api .

3- docker run -p8080:8080 rickandmorty-spring-boot-graphql-api

# Accessing/Testing the developed API

1- http://localhost:8080/graphiql?path=/graphql
2- Postman Collection (Sample postman collection is added to the project named as GrapghQL.postman_collection.json)
3- Through Altair GraphQL Client Extension with path http://localhost:8080/graphql
