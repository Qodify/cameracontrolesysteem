package kdg.be.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
@EnableCaching
public class ProcessorApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessorApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ProcessorApplication.class, args);

    }
/*
    @Bean
    public CommandLineRunner demo(FineRepository repository) {
        return (args) -> {
            // save a couple of customers
            repository.save(new Fine(123, true, new EmissionOffense(LocalDateTime.now(), "1-AZE-123", 4, 4, "")));
            System.out.println(repository.findAll());

        };
    }*/

}
