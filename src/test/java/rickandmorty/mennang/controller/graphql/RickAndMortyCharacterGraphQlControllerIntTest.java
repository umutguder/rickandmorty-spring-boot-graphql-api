package rickandmorty.mennang.controller.graphql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.web.reactive.server.WebTestClient;
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

    // TODO: Tests cases should be added for full coverage

}
