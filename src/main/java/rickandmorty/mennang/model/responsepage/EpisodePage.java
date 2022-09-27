package rickandmorty.mennang.model.responsepage;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import rickandmorty.mennang.model.entity.Episode;

import java.util.List;

@Builder
public class EpisodePage {

    @JsonProperty("results")
    private List<Episode> results;

    @JsonProperty("info")
    private PageInfo<Episode> info;
}
