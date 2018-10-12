package kdg.be.processor.BL.offense;

import kdg.be.processor.Domain.cameramessage.CameraMessage;
import kdg.be.processor.Domain.perception.CameraPercept;
import kdg.be.processor.Domain.perception.LicensePlatePercept;
import org.springframework.stereotype.Component;

@Component
public class SpeedingOffenseCalculator implements IReceiverListener {

  @Override
  public void OnMessageReceived(CameraMessage cm) {

  }
}
