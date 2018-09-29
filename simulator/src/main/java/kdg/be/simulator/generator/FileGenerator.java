package kdg.be.simulator.generator;

import kdg.be.simulator.MyReader;
import kdg.be.simulator.models.CameraMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@ConditionalOnProperty(name = "MessageGeneratorType", havingValue = "file")
public class FileGenerator implements IMessageGenerator {
  @Value("${filePath:.//src//main//resources//textfiles//overtredingen.csv}")
  private String filePath;

  @Override
  public CameraMessage generate() {

    MyReader mr = new MyReader();

    return mr.ReadCSV(filePath).get(0);
  }

  @Override
  public List<CameraMessage> generateList() {
    return null;
  }
}
