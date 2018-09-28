package kdg.be.simulator.messenger;

import kdg.be.simulator.generator.IMessageGenerator;
import kdg.be.simulator.models.CameraMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "MessengerType", havingValue = "QueueMessenger")
public class QueueMessenger implements IMessenger {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  public QueueMessenger(IMessageGenerator messageGenerator) {
  }


  @Override
  public void sendMessage(CameraMessage message) {
    rabbitTemplate.convertAndSend("MessageQueue", message.toString());
  }
}
