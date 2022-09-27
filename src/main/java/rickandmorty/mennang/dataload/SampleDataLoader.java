package rickandmorty.mennang.dataload;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import rickandmorty.mennang.model.entity.Episode;
import rickandmorty.mennang.model.entity.Location;
import rickandmorty.mennang.model.entity.RickAndMortyCharacter;
import rickandmorty.mennang.repository.EpisodeRepository;
import rickandmorty.mennang.repository.LocationRepository;
import rickandmorty.mennang.repository.RickAndMortyCharacterRepository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class SampleDataLoader implements CommandLineRunner {

    private final RickAndMortyCharacterRepository rickAndMortyCharacterRepository;
    private final LocationRepository locationRepository;
    private final EpisodeRepository episodeRepository;

    private final Faker faker;

    public SampleDataLoader(RickAndMortyCharacterRepository rickAndMortyCharacterRepository,
                            LocationRepository locationRepository,
                            EpisodeRepository episodeRepository,
                            Faker faker) {
        this.rickAndMortyCharacterRepository = rickAndMortyCharacterRepository;
        this.locationRepository = locationRepository;
        this.episodeRepository = episodeRepository;
        this.faker = faker;
    }

    @Override
    public void run(String... args) {

        // create 51 random episodes as in rick and morty
        List<Episode> episodes = getEpisodes();

        // create 100 locations as in rick and morty through faker api
        List<Location> locations = getLocations();

        // create 826 characters as in rick and morty through faker api
        List<RickAndMortyCharacter> characters = IntStream.rangeClosed(1, 826)
                .mapToObj(i -> {

                            //Random episode
                            Episode episode = episodes.get(i % episodes.size());

                            //Random location
                            Location location = locations.get(i % locations.size());

                            RickAndMortyCharacter character = RickAndMortyCharacter.builder()
                                    .name(faker.rickAndMorty().character())
                                    .image("https://rickandmortyapi.com/api/character/avatar/" + i + ".jpeg")
                                    .location(location)
                                    .created(LocalDateTime.now())
                                    .episode(episode).build();

                            episode.getCharacters().add(character);
                            location.getResidents().add(character);
                            return character;

                        }
                ).toList();

        episodeRepository.saveAll(episodes);
        locationRepository.saveAll(locations);
        rickAndMortyCharacterRepository.saveAll(characters);

    }

    private List<Location> getLocations() {
        return IntStream.rangeClosed(1, 126)
                .mapToObj(i -> Location.builder()
                        .name(faker.rickAndMorty().location())
                        .residents(new HashSet<>())
                        .build()
                ).toList();
    }

    private List<Episode> getEpisodes() {
        return IntStream.rangeClosed(1, 51)
                .mapToObj(i -> Episode.builder()
                        .name(String.valueOf(i))
                        .characters(new HashSet<>())
                        .created(LocalDateTime.now().minusYears(8).plusDays(7 * i)).build()).toList();
    }
}