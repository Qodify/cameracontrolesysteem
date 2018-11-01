package kdg.be.processor.businesslogic.manager;

import kdg.be.processor.domain.offense.Offense;
import kdg.be.processor.domain.perception.CameraPercept;
import kdg.be.processor.domain.perception.LicensePlatePercept;

import java.util.Optional;

public interface IOffenseManager {
  Optional<Offense> calcOffense(Optional<LicensePlatePercept> lpp, Optional<CameraPercept> cp);
}
