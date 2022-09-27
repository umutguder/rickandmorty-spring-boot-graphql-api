package rickandmorty.mennang.controller.qraphql;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import rickandmorty.mennang.model.entity.RickAndMortyCharacter;
import rickandmorty.mennang.model.filter.CharacterFilter;
import rickandmorty.mennang.model.responsepage.PageInfo;
import rickandmorty.mennang.model.responsepage.PageRequestAdapter;
import rickandmorty.mennang.model.responsepage.RickAndMortyCharacterPage;
import rickandmorty.mennang.repository.RickAndMortyCharacterRepository;

import java.util.List;

@Controller
public class RickAndMortyCharacterGraphQlController {

    private final RickAndMortyCharacterRepository repository;

    public RickAndMortyCharacterGraphQlController(RickAndMortyCharacterRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    public List<RickAndMortyCharacter> charactersByIds(@Argument List<Integer> ids) {
        return repository.findByIdIn(ids);
    }


    @QueryMapping
    public RickAndMortyCharacter character(@Argument int id) {
        return repository.findById(id).orElse(null);
    }

    @QueryMapping()
    public RickAndMortyCharacterPage characters(@Argument CharacterFilter filter,
                                                @Argument int page,
                                                @Argument int size) {

        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();

        Example<RickAndMortyCharacter> example
                = Example.of(RickAndMortyCharacter.builder().name(filter.getName()).build(), matcher);

        Page<RickAndMortyCharacter> result = repository.findAll(example, PageRequestAdapter.of(page, size));

        PageInfo<RickAndMortyCharacter> info = new PageInfo<>(page, result);

        return RickAndMortyCharacterPage.builder()
                .info(info)
                .results(result.toList())
                .build();
    }
}
