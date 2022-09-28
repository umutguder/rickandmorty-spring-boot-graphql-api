package rickandmorty.mennang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rickandmorty.mennang.model.entity.RickAndMortyCharacter;

import java.util.List;

public interface RickAndMortyCharacterRepository extends JpaRepository<RickAndMortyCharacter, Integer> {

    List<RickAndMortyCharacter> findByIdIn(List<Integer> ids);
}
