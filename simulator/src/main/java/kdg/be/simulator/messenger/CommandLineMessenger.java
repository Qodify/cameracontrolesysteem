package kdg.be.simulator.messenger;

import kdg.be.simulator.generator.MessageGenerator;
import kdg.be.simulator.models.CameraMessage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Stream;

@Component
@ConditionalOnProperty(name = "MessengerType", havingValue = "clm")
public class CommandLineMessenger implements Messenger {

  @Override
  public void sendMessage(CameraMessage message) {
    System.out.println(message);
  }
}