package rickandmorty.mennang.controller.qraphql;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import rickandmorty.mennang.model.entity.Location;
import rickandmorty.mennang.model.filter.LocationFilter;
import rickandmorty.mennang.model.responsepage.LocationPage;
import rickandmorty.mennang.model.responsepage.PageInfo;
import rickandmorty.mennang.model.responsepage.PageRequestAdapter;
import rickandmorty.mennang.repository.LocationRepository;

import javax.validation.constraints.Min;
import java.util.List;

@Controller
public class LocationGraphQlController {

    private final LocationRepository repository;

    public LocationGraphQlController(LocationRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    public List<Location> locationsByIds(@Argument List<Integer> ids) {
        return repository.findByIdIn(ids);
    }

    @QueryMapping
    public Location location(@Argument int id) {
        return repository.findById(id).orElse(null);
    }

    @QueryMapping()
    public LocationPage locations(@Argument LocationFilter filter,
                                  @Argument @Min(value = 0, message = "Must be greater than -1") int page,
                                  @Argument @Min(value = 1, message = "Must be greater than 0") int size) {

        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();

        Example<Location> example = Example.of(Location.builder().name(filter.getName()).build(), matcher);

        Page<Location> result = repository.findAll(example, PageRequestAdapter.of(page, size));

        PageInfo<Location> info = new PageInfo<>(page, result);

        return LocationPage.builder()
                .info(info)
                .results(result.toList())
                .build();
    }
}
