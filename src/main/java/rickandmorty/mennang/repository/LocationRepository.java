package rickandmorty.mennang.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import rickandmorty.mennang.model.entity.Location;

import java.util.List;

public interface LocationRepository extends
        PagingAndSortingRepository<Location, Integer>,
        QueryByExampleExecutor<Location> {

    List<Location> findByIdIn(List<Integer> ids);
}
