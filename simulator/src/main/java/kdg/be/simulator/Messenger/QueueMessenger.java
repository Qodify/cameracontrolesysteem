package kdg.be.simulator.Messenger;

import kdg.be.simulator.generator.IMessageGenerator;
import kdg.be.simulator.models.CameraMessage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "MessengerType", havingValue = "QueueMessenger")
public class QueueMessenger implements IMessenger {

  private int frequency;

  public QueueMessenger(IMessageGenerator messageGenerator) {
  }


  @Override
  public void sendMessage(CameraMessage message) {

  }
}
