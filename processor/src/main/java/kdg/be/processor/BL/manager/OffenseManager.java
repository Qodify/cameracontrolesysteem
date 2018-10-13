package kdg.be.processor.BL.manager;

import be.kdg.sa.services.CameraNotFoundException;
import be.kdg.sa.services.InvalidLicensePlateException;
import be.kdg.sa.services.LicensePlateNotFoundException;
import kdg.be.processor.BL.adapter.CameraServiceAdapter;
import kdg.be.processor.Domain.cameramessage.CameraMessage;
import kdg.be.processor.Domain.offense.EmissionOffense;
import kdg.be.processor.Domain.offense.Offense;
import kdg.be.processor.Domain.perception.CameraPercept;
import kdg.be.processor.Domain.perception.LicensePlatePercept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OffenseManager {
  private static final Logger LOGGER = LoggerFactory.getLogger(OffenseManager.class);


  @Autowired
  public OffenseManager() {
  }

  public void calcEmissionOffense(CameraMessage cm, Optional<LicensePlatePercept> lpp, Optional<CameraPercept> cp) {
    try {
      if (lpp.orElseThrow(IllegalStateException::new).getEuroNumber()
          < cp.orElseThrow(IllegalStateException::new).getEuroNorm()) {
        //TODO: writes fine to database
        var offense = new EmissionOffense(lpp.get().getPlateId(), lpp.get().getEuroNumber(), cp.get().getEuroNorm());

      }

    } catch (LicensePlateNotFoundException e) {
      LOGGER.error(e.getMessage());
    } catch (InvalidLicensePlateException e) {
      LOGGER.error(e.getMessage());
    } catch (CameraNotFoundException e) {
      LOGGER.error(e.getMessage());
    }catch (IllegalStateException e){
      LOGGER.warn(e.getMessage());
    }


  }


  public void calcSpeedingOffense(CameraMessage cm, CameraPercept cp) {

  }
}
