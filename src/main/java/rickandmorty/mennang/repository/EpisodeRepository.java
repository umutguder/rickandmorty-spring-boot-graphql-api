package rickandmorty.mennang.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import rickandmorty.mennang.model.entity.Episode;

import java.util.List;

public interface EpisodeRepository extends
        PagingAndSortingRepository<Episode, Integer>,
        QueryByExampleExecutor<Episode> {

    List<Episode> findByIdIn(List<Integer> ids);
}
