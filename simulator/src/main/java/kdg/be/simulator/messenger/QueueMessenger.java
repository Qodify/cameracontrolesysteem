package kdg.be.simulator.messenger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import kdg.be.simulator.generator.MessageGenerator;
import kdg.be.simulator.models.CameraMessage;
import kdg.be.simulator.models.CameraMessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@ConditionalOnProperty(name = "MessengerType", havingValue = "qm")
public class QueueMessenger implements Messenger {

  private static final Logger LOGGER = LoggerFactory.getLogger(QueueMessenger.class);
  private final RabbitTemplate rabbitTemplate;

  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  @Autowired
  public QueueMessenger(MessageGenerator messageGenerator, RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @Override
  public void sendMessage(CameraMessage message) {
    try {
      XmlMapper objectMapper = new XmlMapper();
      String xml = objectMapper.writeValueAsString(new CameraMessageDTO(message));
      rabbitTemplate.convertAndSend("MessageQueue", xml);
      LOGGER.info(xml);
    } catch (IllegalStateException e) {
      LOGGER.trace("Null message is not send to queue.");
    } catch (JsonProcessingException e) {
      LOGGER.error(e.getMessage());
    }
  }
}
