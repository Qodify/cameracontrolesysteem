package kdg.be.processor.BL.offense;

import kdg.be.processor.Domain.perception.CameraPercept;
import kdg.be.processor.Domain.perception.LicensePlatePercept;
import org.springframework.stereotype.Component;

@Component
public class SpeedingOffenseCalculator implements IOffense {
  @Override
  public void calculateFine(CameraPercept cp, LicensePlatePercept lpp) {

  }
}
