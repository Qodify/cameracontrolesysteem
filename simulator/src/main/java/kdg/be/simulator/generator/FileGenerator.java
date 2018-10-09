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
import java.util.Scanner;

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
  }

  @PostConstruct
  public void test() {
    cr.Initialize();
  }

  @Override
  public CameraMessage generate() {
    boolean continueCycle = true;
    do {
      //check if scanner exists
      if (scanner == null) {
        try {
          scanner = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
          LOGGER.error(e.getMessage());
        }
      }
      while (scanner.hasNext()) {
        //fill list with scanner uses method from CSVReader
        List<String> line = parseLine(scanner.nextLine());
        //fills object
        var cm = new CameraMessage(Integer.parseInt(line.get(0)), line.get(1),
            LocalDateTime.now().plusSeconds(Long.parseLong(line.get(2)) / 1000));
        try {
          //delay the message for his given timestamp compared to Now
          Thread.sleep(ChronoUnit.MILLIS.between(LocalDateTime.now(), cm.getTimestamp()));
          //Thread.sleep(1000);
        } catch (InterruptedException e) {
          LOGGER.error(e.getMessage());
        }
        //push cameramessage to queue
        return cm;
      }
      //nullify scanner to read csv again
      scanner = null;
    } while (continueCycle);

    return null;
  }


}
