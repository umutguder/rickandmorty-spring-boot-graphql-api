package rickandmorty.mennang.model.responsepage;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import rickandmorty.mennang.model.entity.Episode;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EpisodePage {

    @JsonProperty("results")
    private List<Episode> results;

    @JsonProperty("info")
    private PageInfo<Episode> info;
}
