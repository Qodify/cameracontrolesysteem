package kdg.be.simulator.Messenger;

import kdg.be.simulator.generator.IMessageGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "MessengerType", havingValue = "QueueMessenger")

public class QueueMessenger implements IMessenger {
  private final IMessageGenerator messageGenerator;
  private int frequency;

  public QueueMessenger(IMessageGenerator messageGenerator) {
    this.messageGenerator = messageGenerator;
  }



  @Override
  public void sendMessage() {
    messageGenerator.generate();

  }


}
