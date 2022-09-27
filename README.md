#  The Back-End Challenge:

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
                     characters(page: 2, filter: { name: "rick" }) {
                         info {
                             count
                         }
                         results {
                             name
                         }
                     }
                     
                     locations(page: 2, filter: { name: "rick" }) {
                         info {
                             count
                         }
                         results {
                             name
                         }
                     }
                     
                     episodes(page: 2, filter: { name: "rick" }) {
                         info {
                             count
                         }
                         results {
                             name
                         }
                     }
                     
                     # By Id
                     character(id: 1) {
                         id
                     }
                     
                     location(id: 1) {
                         id
                     }
                     
                     episode(id: 1) {
                         id
                     }
                     
                     # By Ids
                     charactersByIds(ids: [1, 2]) {
                         id
                     }
                     locationsByIds(ids: [1, 2]) {
                         id
                     }
                     episodesByIds(ids: [1, 2]) {
                         id
                     }