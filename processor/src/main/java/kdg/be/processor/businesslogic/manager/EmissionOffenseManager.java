package kdg.be.processor.businesslogic.manager;

import be.kdg.sa.services.CameraNotFoundException;
import be.kdg.sa.services.InvalidLicensePlateException;
import be.kdg.sa.services.LicensePlateNotFoundException;
import kdg.be.processor.businesslogic.service.FineService;
import kdg.be.processor.domain.offense.EmissionOffense;
import kdg.be.processor.domain.offense.Offense;
import kdg.be.processor.domain.perception.CameraPercept;
import kdg.be.processor.domain.perception.LicensePlatePercept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class EmissionOffenseManager implements IOffenseManager {
  private static final Logger LOGGER = LoggerFactory.getLogger(EmissionOffenseManager.class);

  private List<EmissionOffense> emissionOffenseList;

  private final FineService fineService;

  @Value(value = "${graceperiodinseconds}")
  private Long gracePeriodInSeconds;

  public EmissionOffenseManager(FineService fineService) {
    emissionOffenseList = new LinkedList<>();
      this.fineService = fineService;
  }

  /**
   * This method will calculate wether an EmissionOffense occured
   * It wil only generate a fine if the EmissionOffense is genrated for the first time
   * or when the same licenseplate violates the offense again and falls out of the graceperiod
   * gracePeriodInSeconds is cafingurable in application.properties
   */
  @Override
  public Optional<Offense> calcOffense(Optional<LicensePlatePercept> lpp, Optional<CameraPercept> cp) {
    try {
      if (lpp.orElseThrow(IllegalStateException::new).getEuroNumber()
              < cp.orElseThrow(IllegalStateException::new).getEuroNorm()) {
        EmissionOffense o = new EmissionOffense(LocalDateTime.now(), lpp.get().getPlateId(), lpp.get().getEuroNumber(), cp.get().getEuroNorm());

        emissionOffenseList.add(o);
        for (EmissionOffense e : emissionOffenseList) {
          if (e.getLicenseplate().equals(o.getLicenseplate())) {
            if (e.getTimestamp().getSecond() > o.getTimestamp().getSecond() + gracePeriodInSeconds) {
              return Optional.empty();
            }
          }
        }
        return Optional.of(o);
      }
    } catch (LicensePlateNotFoundException | InvalidLicensePlateException | IllegalStateException
            | CameraNotFoundException | InvalidDataAccessApiUsageException e) {
      LOGGER.warn(e.getMessage());
    }
    return Optional.empty();
  }
}

