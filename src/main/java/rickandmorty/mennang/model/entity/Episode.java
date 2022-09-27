package rickandmorty.mennang.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Episode {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "episode", fetch = FetchType.EAGER)
    private Set<RickAndMortyCharacter> characters = new HashSet<>();

    private LocalDateTime created;
}
