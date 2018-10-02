package kdg.be.simulator.generator;

import kdg.be.simulator.fileReader.CSVReader;
import kdg.be.simulator.models.CameraMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnProperty(name = "MessageGeneratorType", havingValue = "file")
public class FileGenerator implements IMessageGenerator {
  @Value("${filePath:.//src//main//resources//textfiles//overtredingen.csv}")
  private String filePath;
  private CSVReader cr;

  @Autowired
  public FileGenerator(CSVReader cr) {
    this.cr = cr;
  }

  @Override
  public CameraMessage generate() {

    List<CameraMessage> list = cr.ReadCSV(filePath);
    return list.get(0);
  }

}
