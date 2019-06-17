package kdg.be.processor.businesslogic.retrieval;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import kdg.be.processor.domain.cameramessage.CameraMessage;
import kdg.be.processor.domain.cameramessage.CameraMessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
  private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

  private MessageDelegator messageDelegator;

  @Autowired
  public Receiver(MessageDelegator messageDelegator) {
    this.messageDelegator = messageDelegator;
  }

  @Bean
  SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames("MessageQueue");
    container.setMessageListener(listenerAdapter);
    return container;
  }

  @Bean
  MessageListenerAdapter listenerAdapter(Receiver receiver) {
    return new MessageListenerAdapter(receiver, "receiveMessage");
  }

  public void receiveMessage(String xmlMessage) {

    LOGGER.info("Received xml <" + xmlMessage + ">");
    try {
      XmlMapper xmlMapper = new XmlMapper();
      var cmDTO = xmlMapper.readValue(xmlMessage, CameraMessageDTO.class);
      var cm = new CameraMessage(cmDTO);
      LOGGER.info("Parced <" + cm.toString() + ">");
      //TODO: niet direct naar
      messageDelegator.messageHandler(cm);
    } catch (Exception e) {
      LOGGER.error("Incoming Message mismatches with parsing rules, check the toString method of the messageDTO " + e.getMessage());
    }
  }

}