package kdg.be.simulator.messenger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import kdg.be.simulator.generator.IMessageGenerator;
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
public class QueueMessenger implements IMessenger {
  private static final Logger LOGGER = LoggerFactory.getLogger(QueueMessenger.class);

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  public QueueMessenger(IMessageGenerator messageGenerator) {
  }


  @Override
  public void sendMessage(Optional<CameraMessage> message) {
    CameraMessageDTO cmDTO;
    String xml;
    try {
      cmDTO = new CameraMessageDTO(message.orElseThrow(IllegalStateException::new));
      xml = cmDTO.toString();
      var objectMapper = new XmlMapper();
      xml = objectMapper.writeValueAsString(cmDTO);
      rabbitTemplate.convertAndSend("MessageQueue", xml);
    } catch (IllegalStateException e) {
      LOGGER.error("message is nullreferenced");
    } catch (JsonProcessingException e) {
      LOGGER.error(e.getMessage());
    }
  }
}
