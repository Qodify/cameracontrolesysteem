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
  public void sendMessage(CameraMessage message) {
    CameraMessageDTO cmDTO
        = new CameraMessageDTO(message);
    ObjectMapper objectMapper = new XmlMapper();
    String xml = cmDTO.toString();
    try {

      xml = objectMapper.writeValueAsString(cmDTO);
    } catch (JsonProcessingException e) {
      //kdg.be.simulator.Domain.CameraMessageDTO@3a96aa35
      System.out.println(xml);
      LOGGER.error(e.getMessage());
    }

    rabbitTemplate.convertAndSend("MessageQueue", xml);
  }
}
