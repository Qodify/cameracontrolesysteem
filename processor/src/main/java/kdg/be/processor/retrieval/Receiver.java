package kdg.be.processor.retrieval;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import kdg.be.processor.models.CameraMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Receiver {
  private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

  @Autowired
//  private OvertredingListener controller;
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
    LOGGER.info("Received <" + xmlMessage + ">");
    try {
//      int id = Integer.parseInt(message.substring(15, 19).trim());
//      String plate=message.substring(20,29 );
//      String date=message.substring(32,57);
//      LocalDateTime dateTime = LocalDateTime.parse(date, CameraMessage.getFORMATTER());
      XmlMapper xmlMapper = new XmlMapper();
      CameraMessage cm = xmlMapper.readValue(xmlMessage, CameraMessage.class);

      LOGGER.info("Parced <" + cm.toString() + ">");
//      controller.addToList(cameraMessage);
    } catch (Exception e) {
      LOGGER.error("Incoming Message mismatches with parsing rules, check the toString method of the simulator");
      e.printStackTrace();
      System.exit(65);
    }
  }
}