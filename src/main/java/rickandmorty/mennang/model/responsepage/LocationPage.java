package rickandmorty.mennang.model.responsepage;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import rickandmorty.mennang.model.entity.Location;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationPage {

    @JsonProperty("results")
    private List<Location> results;

    @JsonProperty("info")
    private PageInfo<Location> info;
}
