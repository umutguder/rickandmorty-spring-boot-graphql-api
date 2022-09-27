package rickandmorty.mennang;

import com.github.javafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RickAndMortySpringBootGraphqlApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RickAndMortySpringBootGraphqlApiApplication.class, args);
    }

    //Fake data api
    @Bean
    public Faker faker() {
        return new Faker();
    }
}
