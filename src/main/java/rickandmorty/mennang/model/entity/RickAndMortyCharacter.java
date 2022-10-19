package rickandmorty.mennang.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RickAndMortyCharacter {

    @Id
    @GeneratedValue
    private Integer id;

    @NotEmpty(message = "Must be not empty")
    private String name;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @ManyToOne
    @JoinColumn(name = "episode_id", nullable = false)
    private Episode episode;

    @NotNull
    private String image;

    @NotNull
    private LocalDateTime created;
}