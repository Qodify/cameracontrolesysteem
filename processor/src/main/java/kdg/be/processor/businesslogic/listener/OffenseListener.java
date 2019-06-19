package kdg.be.processor.businesslogic.listener;

import kdg.be.processor.domain.cameramessage.CameraMessage;
import kdg.be.processor.domain.offense.Offense;

import java.io.IOException;
import java.util.Optional;

public interface OffenseListener {
  Optional<Offense> onMessageReceived(CameraMessage cm) throws IOException;
}
