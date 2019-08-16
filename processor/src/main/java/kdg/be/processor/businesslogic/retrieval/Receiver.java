package kdg.be.processor.businesslogic.retrieval;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import kdg.be.processor.domain.cameramessage.CameraMessage;
import kdg.be.processor.frontend.dto.CameraMessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Receiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private Notifier notifier;

    @Autowired
    public Receiver(Notifier notifier) {
        this.notifier = notifier;
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
            CameraMessageDTO cmDTO = xmlMapper.readValue(xmlMessage, CameraMessageDTO.class);
            CameraMessage cm = new CameraMessage(cmDTO);

            LOGGER.info("Parced <" + cm.toString() + ">");
            notifier.notifyListeners(cm);

        } catch (JsonParseException e) {
            LOGGER.error("Incoming Message mismatches with parsing rules, check the toString method of the messageDTO " + e.getMessage());
        } catch (JsonMappingException e) {
            LOGGER.error("Incoming Message mismatches with parsing rules, check the toString method of the messageDTO " + e.getMessage());
        } catch (IOException e) {
            LOGGER.error("error handling incoming stream of data " + e.getMessage());
        }
    }

}