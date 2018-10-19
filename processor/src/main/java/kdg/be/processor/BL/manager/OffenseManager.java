package kdg.be.processor.BL.manager;

import be.kdg.sa.services.CameraNotFoundException;
import be.kdg.sa.services.InvalidLicensePlateException;
import be.kdg.sa.services.LicensePlateNotFoundException;
import kdg.be.processor.DAL.OffenseRepository;
import kdg.be.processor.Domain.offense.EmissionOffense;
import kdg.be.processor.Domain.offense.SpeedingOffense;
import kdg.be.processor.Domain.perception.CameraPercept;
import kdg.be.processor.Domain.perception.LicensePlatePercept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OffenseManager {
  private static final Logger LOGGER = LoggerFactory.getLogger(OffenseManager.class);
  @Value("${speedingFineFactor:30}")
  private double speedingFineFactor;
  private OffenseRepository offenseRepo;

  @Autowired
  public OffenseManager(OffenseRepository offenseRepo) {
    this.offenseRepo = offenseRepo;

  }

  public void calcEmissionOffense(Optional<LicensePlatePercept> lpp, Optional<CameraPercept> cp) {
    try {
      if (lpp.orElseThrow(IllegalStateException::new).getEuroNumber()
              < cp.orElseThrow(IllegalStateException::new).getEuroNorm()) {
        var offense = new EmissionOffense(lpp.get().getPlateId(), lpp.get().getEuroNumber(), cp.get().getEuroNorm(), 345);
        offenseRepo.save(offense);
      }
    } catch (LicensePlateNotFoundException | InvalidLicensePlateException | IllegalStateException
            | CameraNotFoundException | InvalidDataAccessApiUsageException e) {
      LOGGER.error(e.getMessage());
    }
  }


  public void calcSpeedingOffense(List<CameraPercept> cpList) {
    //bedrag = (snelheid – maximaal toegelaten snelheid in de zone) * snelheid boetefacto
  }

  public void addEmissionOffense(EmissionOffense emissionOffense) {
    offenseRepo.save(emissionOffense);
  }

  public void addSpeedingOffense(SpeedingOffense speedingOffense, double speedingFineFactor) {
    offenseRepo.save(speedingOffense);
  }
}
