package kdg.be.processor.BL.offense;

import kdg.be.processor.Domain.perception.CameraPercept;
import kdg.be.processor.Domain.perception.LicensePlatePercept;

public interface IOffense {

  public void calculateFine(CameraPercept cp, LicensePlatePercept lpp);
}
