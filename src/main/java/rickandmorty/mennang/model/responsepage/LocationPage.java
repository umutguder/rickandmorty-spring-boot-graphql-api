package rickandmorty.mennang.model.responsepage;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import rickandmorty.mennang.model.entity.Location;

import java.util.List;

@Builder
public class LocationPage {

    @JsonProperty("results")
    private List<Location> results;

    @JsonProperty("info")
    private PageInfo<Location> info;
}
