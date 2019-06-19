package kdg.be.processor.businesslogic.retrieval;

import be.kdg.sa.services.CameraNotFoundException;
import kdg.be.processor.businesslogic.listener.OffenseListener;
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
public class Notifier {
  private static final Logger LOGGER = LoggerFactory.getLogger(Notifier.class);
  private final List<OffenseListener> listeners;
  private FineService fineService;

  @Autowired
  public Notifier(List<OffenseListener> listeners, FineService fineService) {
    this.listeners = listeners;
    this.fineService = fineService;
  }


  void notifyListeners(CameraMessage cm) {

    listeners.stream()
            .map(
                    l -> {
                      try {
                        return l.onMessageReceived(cm);
                      } catch (IOException e) {
                        LOGGER.warn(e.getMessage() + " : " + l.toString() + " : " + cm);
                      } catch(CameraNotFoundException cmnfe){
                          LOGGER.warn(cmnfe.getMessage());
                      }
                      return Optional.<Offense>empty();
                    }
            )
            .filter(Optional::isPresent)
            .map(Optional::get)
            .forEach(fineService::add);
  }
}


