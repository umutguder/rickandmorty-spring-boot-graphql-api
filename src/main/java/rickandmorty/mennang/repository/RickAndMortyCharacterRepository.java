package rickandmorty.mennang.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import rickandmorty.mennang.model.entity.RickAndMortyCharacter;

import java.util.List;

public interface RickAndMortyCharacterRepository extends
        PagingAndSortingRepository<RickAndMortyCharacter, Integer>,
        QueryByExampleExecutor<RickAndMortyCharacter> {

    List<RickAndMortyCharacter> findByIdIn(List<Integer> ids);
}
