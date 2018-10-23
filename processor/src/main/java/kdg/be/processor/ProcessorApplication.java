package kdg.be.processor;

import kdg.be.processor.BL.manager.FineManager;
import kdg.be.processor.DAL.FineRepository;
import kdg.be.processor.Domain.fine.Fine;
import kdg.be.processor.Domain.offense.EmissionOffense;
import org.apache.tomcat.jni.Local;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDateTime;

@EnableJpaRepositories
@SpringBootApplication
public class ProcessorApplication {
  private static final Logger LOGGER = LoggerFactory.getLogger(ProcessorApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(ProcessorApplication.class, args);
  }


  //debugging code --> delete when opleveren
  //TODO: delete this
  @Bean
  public CommandLineRunner demo(FineRepository repo, FineManager fineManager) {
    return (args) -> {
      Fine fine = new Fine(new EmissionOffense(LocalDateTime.now(), "ABC-123-1", 3, 4), 123, LocalDateTime.now());
      Fine fine2 = new Fine(new EmissionOffense(LocalDateTime.now(), "ABC-123-2", 3, 4), 123, LocalDateTime.now());
      Fine fine3 = new Fine(new EmissionOffense(LocalDateTime.now(), "ABC-123-3", 3, 4), 123, LocalDateTime.now());
      repo.save(fine);
      repo.save(fine2);
      repo.save(fine3);
//      LOGGER.error(fineManager.load((long)1).get().toString()); //Fine(id=1, bedrag=123.0, offense=EmissionOffense{Id=2, licenseplate='ABC-123-1', carEuronorm=3, emissionZoneEuronorm=4})
    };
  }
}
