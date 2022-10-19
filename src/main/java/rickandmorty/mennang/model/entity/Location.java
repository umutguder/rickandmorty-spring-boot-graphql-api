package rickandmorty.mennang.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location {

    @Id
    @GeneratedValue
    private Integer id;

    @NotEmpty(message = "Must be not empty")
    private String name;

    @NotNull
    private LocalDateTime created;

    @OneToMany(mappedBy = "location", fetch = FetchType.EAGER)
    private Set<RickAndMortyCharacter> residents = new HashSet<>();

}
