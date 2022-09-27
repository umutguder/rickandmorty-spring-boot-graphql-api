package rickandmorty.mennang.controller.graphql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;
import rickandmorty.mennang.controller.qraphql.RickAndMortyCharacterGraphQlController;
import rickandmorty.mennang.repository.RickAndMortyCharacterRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/*
    Slice test for only focussed part (ProductController) with fast response
     Dont load all app context
     Dont load Component Service repository
     Only load required like controller
*/
@GraphQlTest(RickAndMortyCharacterGraphQlController.class)
class RickAndMortyCharacterGraphQlControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private RickAndMortyCharacterRepository repository;

    @Test
    void contextLoads() {
        assertNotNull(graphQlTester);
        assertNotNull(repository);
    }
}