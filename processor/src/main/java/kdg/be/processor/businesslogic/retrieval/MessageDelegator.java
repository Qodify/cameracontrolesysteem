package kdg.be.processor.businesslogic.retrieval;

import kdg.be.processor.businesslogic.listener.IReceiverListener;
import kdg.be.processor.businesslogic.service.FineService;
import kdg.be.processor.domain.cameramessage.CameraMessage;
import kdg.be.processor.domain.offense.Offense;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class MessageDelegator {
  private static final Logger LOGGER = LoggerFactory.getLogger(MessageDelegator.class);
  private final List<IReceiverListener> listListener;
  private FineService fineService;

  @Autowired
  public MessageDelegator(List<IReceiverListener> listListener, FineService fineService) {
    this.listListener = listListener;
    this.fineService = fineService;
  }


  void messageHandler(CameraMessage cm) {

    listListener.stream()
            .map(
                    l -> {
                      try {
                        return l.onMessageReceived(cm);
                      } catch (IOException e) {
                        LOGGER.warn(e.getMessage() + " : " + l.toString() + " : " + cm);
                      }
                      return Optional.<Offense>empty();
                    }
            )
            .filter(Optional::isPresent)
            .map(Optional::get)
            .forEach(fineService::add);


  }
}


