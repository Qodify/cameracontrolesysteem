package kdg.be.processor;

import kdg.be.processor.BL.manager.OffenseManager;
import kdg.be.processor.DAL.EmissionOffenseRepository;
import kdg.be.processor.Domain.offense.EmissionOffense;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProcessorApplication {
  private static final Logger LOGGER = LoggerFactory.getLogger(ProcessorApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(ProcessorApplication.class, args);
  }

  //debugging code --> H2 db works!!
//  @Bean
//  public CommandLineRunner demo(EmissionOffenseRepository repo) {
//    return (args) -> {
//      repo.save(new EmissionOffense("1-ABC-123", 4, 5));
//      var v = repo.findByLicenseplate("1-ABC-123").get();
//      LOGGER.error(v.toString());
//    };
//  }
}
