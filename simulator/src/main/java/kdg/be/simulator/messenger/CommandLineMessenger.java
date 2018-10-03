package kdg.be.simulator.messenger;

import kdg.be.simulator.generator.IMessageGenerator;
import kdg.be.simulator.models.CameraMessage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@ConditionalOnProperty(name = "MessengerType", havingValue = "clm")
public class CommandLineMessenger implements IMessenger {

  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  public CommandLineMessenger(IMessageGenerator messageGenerator) {
  }

  @Override
  public void sendMessage(CameraMessage message) {
    System.out.println(message);

  }
}