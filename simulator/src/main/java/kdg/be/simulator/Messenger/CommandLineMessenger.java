package kdg.be.simulator.Messenger;

import kdg.be.simulator.generator.IMessageGenerator;
import kdg.be.simulator.models.CameraMessage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "MessengerType", havingValue = "CommandLineMessenger")
public class CommandLineMessenger implements IMessenger {


  public CommandLineMessenger( IMessageGenerator messageGenerator) {
  }


  @Override
  public void sendMessage(CameraMessage message) {
  }

}
