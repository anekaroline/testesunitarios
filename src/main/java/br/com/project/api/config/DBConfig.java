package br.com.project.api.config;

import br.com.project.api.domain.User;
import br.com.project.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class DBConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public CommandLineRunner startDB() {
        return args -> {
            User u1 = new User(null, "Ane", "ane@email.com", "123");
            User u2 = new User(null, "Paulo", "paulo@email.com", "123");

            userRepository.saveAll(List.of(u1, u2));
        };
    }
}
