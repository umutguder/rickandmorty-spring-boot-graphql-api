package rickandmorty.mennang.controller.qraphql;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import rickandmorty.mennang.model.entity.Episode;
import rickandmorty.mennang.model.filter.EpisodeFilter;
import rickandmorty.mennang.model.responsepage.EpisodePage;
import rickandmorty.mennang.model.responsepage.PageInfo;
import rickandmorty.mennang.model.responsepage.PageRequestAdapter;
import rickandmorty.mennang.repository.EpisodeRepository;

import javax.validation.constraints.Min;
import java.util.List;

@Controller
public class EpisodeGraphQlController {

    private final EpisodeRepository repository;

    public EpisodeGraphQlController(EpisodeRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    public List<Episode> episodesByIds(@Argument List<Integer> ids) {
        return repository.findByIdIn(ids);
    }

    @QueryMapping
    public Episode episode(@Argument int id) {
        return repository.findById(id).orElse(null);
    }

    @QueryMapping()
    public EpisodePage episodes(@Argument EpisodeFilter filter,
                                @Argument @Min(value = 0, message = "Must be greater than -1") int page,
                                @Argument @Min(value = 1, message = "Must be greater than 0") int size) {

        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();

        Example<Episode> example = Example.of(Episode.builder().name(filter.getName()).build(), matcher);

        Page<Episode> result = repository.findAll(example, PageRequestAdapter.of(page, size));

        PageInfo<Episode> info = new PageInfo<>(page, result);

        return EpisodePage.builder()
                .info(info)
                .results(result.toList())
                .build();
    }
}
