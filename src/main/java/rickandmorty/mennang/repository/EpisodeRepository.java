package rickandmorty.mennang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rickandmorty.mennang.model.entity.Episode;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, Integer> {

    List<Episode> findByIdIn(List<Integer> ids);
}
