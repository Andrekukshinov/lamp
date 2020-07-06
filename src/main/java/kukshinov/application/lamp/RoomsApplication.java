package kukshinov.application.lamp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("kukshinov.application.lamp.repository")
public class RoomsApplication {
    public static void main(String[] args) {
        SpringApplication.run(RoomsApplication.class, args);
    }
}
