package kdg.be.simulator.generator;

import kdg.be.simulator.models.CameraMessage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@ConditionalOnProperty(name = "MessageGeneratorType", havingValue = "file")
public class FileGenerator implements IMessageGenerator {

  @Override
  public CameraMessage generate() {
    return new CameraMessage(2, "1-ABC-123", LocalDateTime.now());
  }
}
