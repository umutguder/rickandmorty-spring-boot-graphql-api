package rickandmorty.mennang.controller.graphql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.web.reactive.server.WebTestClient;
import rickandmorty.mennang.model.entity.Episode;
import rickandmorty.mennang.model.entity.Location;
import rickandmorty.mennang.model.entity.RickAndMortyCharacter;
import rickandmorty.mennang.model.responsepage.EpisodePage;
import rickandmorty.mennang.model.responsepage.LocationPage;
import rickandmorty.mennang.model.responsepage.RickAndMortyCharacterPage;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Integration Test with: HttpGraphQlTester
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RickAndMortyCharacterGraphQlControllerIntTest {

    @LocalServerPort
    int port;
    private HttpGraphQlTester graphQlTester;

    @BeforeEach
    void setUp() {
        WebTestClient client = WebTestClient.bindToServer()
                .baseUrl(String.format("http://localhost:%s/graphql", port))
                .build();

        graphQlTester = HttpGraphQlTester.create(client);
    }

    @Test
    void contextLoads() {
        assertNotNull(graphQlTester);
    }

    @Test
    void shouldRespondWithNullCharacter() {
        /* language=GraphQL */
        String document = """
                    query getCharacters($id: Int) {
                        character(id: $id) {
                            name
                        }
                }
                """;

        graphQlTester.document(document)
                .variable("id", -1)
                .execute()
                .path("character")
                .valueIsNull();
    }

    @Test
    void shouldRespondWithRickAndMortyCharacterPage() {
        /* language=GraphQL */
        String document = """
                    query getCharacters($page: Int) {
                        characters(filter: {}, page: $page) {
                            results {
                                    name
                            }
                            info {
                                    count
                            }
                        }
                }
                """;

        graphQlTester.document(document)
                .variable("page", 0)
                .execute()
                .path("characters")
                .entity(RickAndMortyCharacterPage.class).isNotEqualTo(null);
    }

    @Test
    void shouldRespondWithPagedData() {

        /* language=GraphQL */
        String document = """
                 query {
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
                 }
                """;

        graphQlTester.document(document).execute()
                .path("characters").entity(RickAndMortyCharacterPage.class).isNotEqualTo(null);

        graphQlTester.document(document).execute()
                .path("locations").entity(LocationPage.class).isNotEqualTo(null);

        graphQlTester.document(document).execute()
                .path("episodes").entity(EpisodePage.class).isNotEqualTo(null);
    }


    @Test
    void shouldRespondWithQueriedResultById() {

        /* language=GraphQL */
        String document = """
                 query {
                     # By Id
                     character(id: 998) {
                         id
                     }

                 }
                """;

        graphQlTester.document(document).execute()
                .path("character").entity(RickAndMortyCharacter.class);
    }

    @Test
    void shouldRespondWithQueriedResultByIdEntityList() {

        /* language=GraphQL */
        String document = """
                 query {
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
                 }
                """;

        //By id
        graphQlTester.document(document).execute()
                .path("charactersByIds").entityList(RickAndMortyCharacter.class);

        graphQlTester.document(document).execute()
                .path("locationsByIds").entityList(Location.class).isNotEqualTo(null);

        graphQlTester.document(document).execute()
                .path("episodesByIds").entityList(Episode.class).isNotEqualTo(null);
    }
}
