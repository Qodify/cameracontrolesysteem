package kdg.be.processor.BL.manager;

import be.kdg.sa.services.CameraNotFoundException;
import be.kdg.sa.services.InvalidLicensePlateException;
import be.kdg.sa.services.LicensePlateNotFoundException;
import kdg.be.processor.DAL.FineRepository;
import kdg.be.processor.Domain.fine.Fine;
import kdg.be.processor.Domain.offense.EmissionOffense;
import kdg.be.processor.Domain.offense.Offense;
import kdg.be.processor.Domain.offense.SpeedingOffense;
import kdg.be.processor.Domain.perception.CameraPercept;
import kdg.be.processor.Domain.perception.LicensePlatePercept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class OffenseManager {
  private static final Logger LOGGER = LoggerFactory.getLogger(OffenseManager.class);
  @Value("${speedingFineFactor:30}")
  private double speedingFineFactor;
  private FineRepository fineRepository;
  private FineManager fineManager;

  @Autowired
  public OffenseManager(FineRepository fineRepository, FineManager fineManager) {
    this.fineRepository = fineRepository;
    this.fineManager = fineManager;
  }

  public void calcEmissionOffense(Optional<LicensePlatePercept> lpp, Optional<CameraPercept> cp) {
    try {
      if (lpp.orElseThrow(IllegalStateException::new).getEuroNumber()
              < cp.orElseThrow(IllegalStateException::new).getEuroNorm()) {
        var o = new EmissionOffense(LocalDateTime.now(), lpp.get().getPlateId(), lpp.get().getEuroNumber(),
                cp.get().getEuroNorm());
        fineManager.add(o);
      }
    } catch (LicensePlateNotFoundException | InvalidLicensePlateException | IllegalStateException
            | CameraNotFoundException | InvalidDataAccessApiUsageException e) {
      LOGGER.error(e.getMessage());
    }
  }

  public void save(Fine fine) {
//    try {
//      fineRepository.save();
//    } catch () {
//
//    }
  }


}
