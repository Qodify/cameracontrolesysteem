package kdg.be.processor.BL.retrieval;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import kdg.be.processor.BL.listener.OffenseController;
import kdg.be.processor.Domain.cameramessage.CameraMessage;
import kdg.be.processor.Domain.cameramessage.CameraMessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Receiver {
  private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

  @Autowired
  private List<OffenseController> listListener;

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
      CameraMessage cm = new CameraMessage(cmDTO);
      LOGGER.info("Parced <" + cm.toString() + ">");
      listListener.forEach(l -> l.MessageHandler(cm));
    } catch (Exception e) {
      LOGGER.error("Incoming Message mismatches with parsing rules, check the toString method of the messageDTO" + e.getMessage());

      System.exit(65);
    }
  }
}