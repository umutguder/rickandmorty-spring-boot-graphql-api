package rickandmorty.mennang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rickandmorty.mennang.model.entity.Location;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer> {

    List<Location> findByIdIn(List<Integer> ids);
}
