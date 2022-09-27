package rickandmorty.mennang.model.responsepage;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import rickandmorty.mennang.model.entity.RickAndMortyCharacter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RickAndMortyCharacterPage {

    @JsonProperty("results")
    private List<RickAndMortyCharacter> results = null;

    @JsonProperty("info")
    private PageInfo<RickAndMortyCharacter> info;
}
