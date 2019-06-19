package kdg.be.simulator.generator;

import kdg.be.simulator.fileReader.CSVReader;
import kdg.be.simulator.models.CameraMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

import static kdg.be.simulator.fileReader.CSVReader.parseLine;

@Component
@ConditionalOnProperty(name = "MessageGeneratorType", havingValue = "file")
public class FileGenerator implements IMessageGenerator {

  private static final Logger LOGGER = LoggerFactory.getLogger(FileGenerator.class);

  @Value("${filePath:.//src//main//resources//textfiles//overtredingen.csv}")
  private String filePath;
  private CSVReader cr;
  private Scanner scanner;

  @Autowired
  public FileGenerator(CSVReader cr) {
    this.cr = cr;
    cr.Initialize();
  }




  @Override
  public Optional<CameraMessage> generate() {
    scanner = null;
    do {
      try {
        scanner = new Scanner(new File(filePath));
      } catch (FileNotFoundException e) {
        LOGGER.error(e.getMessage());
      }


      List<String> line = parseLine(scanner.nextLine());

      if (line.get(1).matches("^[0-9]-[A-Z]{3}-[0-9]{3}")) {

        CameraMessage cm = new CameraMessage(Integer.parseInt(line.get(0)), line.get(1),
                LocalDateTime.now().plusSeconds(Long.parseLong(line.get(2)) / 1000));

        try {
          if (Long.parseLong(line.get(2)) <= 0) {
            Thread.sleep(1);

          } else{
            Thread.sleep(ChronoUnit.MILLIS.between(LocalDateTime.now(), cm.getTimestamp()));
          }
        } catch (InterruptedException | IllegalArgumentException e) {
          LOGGER.error(e.getMessage());
        }
        return Optional.of(cm);
      } else {
        LOGGER.error(line.get(1) + "Is not a valid licenseplate. LicensePlate must match ^[0-9]-[A-" +
                "Z]{3}-[0-9]{3}" + " Check your csv file.");
      }
    } while (scanner.hasNext());
    return Optional.empty();
  }
}



