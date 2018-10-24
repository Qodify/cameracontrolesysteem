package kdg.be.processor.BL.manager;

import be.kdg.sa.services.CameraNotFoundException;
import be.kdg.sa.services.InvalidLicensePlateException;
import be.kdg.sa.services.LicensePlateNotFoundException;
import javafx.scene.Camera;
import kdg.be.processor.BL.service.FineService;
import kdg.be.processor.DAL.FineRepository;
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
import java.time.temporal.ChronoUnit;
import java.util.*;

@Component
public class OffenseManager {
  private static final Logger LOGGER = LoggerFactory.getLogger(OffenseManager.class);
  @Value("${speedingFineFactor:30}")
  private double speedingFineFactor;
  private FineService fineService;
  @Value(value = "${graceperiodinseconds}")
  private Long gracePeriodInSeconds;
  private List<EmissionOffense> emissionOffenseList;
  private Map<String, CameraPercept> cameraMessages;

  @Autowired
  public OffenseManager(FineService fineService) {
    emissionOffenseList = new LinkedList<>();
    this.fineService = fineService;
    cameraMessages = new HashMap<>();
  }

  /**
   * This method will calculate wether an EmissionOffense occured
   * It wil only generate a fine if the EmissionOffense is genrated for the first time
   * or when the same licenseplate violates the offense again and falls out of the graceperiod
   * gracePeriodInSeconds is cafingurable in application.properties
   */
  public void calcEmissionOffense(Optional<LicensePlatePercept> lpp, Optional<CameraPercept> cp) {
    try {
      if (lpp.orElseThrow(IllegalStateException::new).getEuroNumber()
              < cp.orElseThrow(IllegalStateException::new).getEuroNorm()) {
        var o = new EmissionOffense(LocalDateTime.now(), lpp.get().getPlateId(), lpp.get().getEuroNumber(),
                cp.get().getEuroNorm());
        emissionOffenseList.add(o);
        for (EmissionOffense e : emissionOffenseList) {
          if (e.getLicenseplate().equals(o.getLicenseplate())) {
            if (e.getTimestamp().getSecond() > o.getTimestamp().getSecond() + gracePeriodInSeconds) {
              return;
            }
          }
        }
        fineService.add(o);
      }
    } catch (LicensePlateNotFoundException | InvalidLicensePlateException | IllegalStateException
            | CameraNotFoundException | InvalidDataAccessApiUsageException e) {
      LOGGER.warn(e.getMessage());
    }
  }

  public Optional<Offense> checkSpeedingOffense(Optional<LicensePlatePercept> lpp, Optional<CameraPercept> cp) {
    try {
      CameraPercept cameraPercept = cp.orElseThrow(IllegalStateException::new);
      String plateId = lpp.orElseThrow(IllegalStateException::new).getPlateId();
      if (cameraMessages.containsKey(plateId))
        return calcSpeedinOffense(cameraMessages.remove(plateId), cp.get(), plateId);
      else if (cameraPercept.getSpeedLimit() != 0) {
        cameraMessages.put(plateId, cameraPercept);
        return Optional.empty();
      } else {
        return Optional.empty();
      }
    } catch (LicensePlateNotFoundException | InvalidLicensePlateException | IllegalStateException
            | CameraNotFoundException | InvalidDataAccessApiUsageException e) {
      LOGGER.warn(e.getMessage());
    }
    return Optional.empty();
  }


  private Optional<Offense> calcSpeedinOffense(CameraPercept cp1, CameraPercept cp2, String plateId) {
    double speedLimit = cp1.getSpeedLimit();
    double distance = cp1.getDistance();
    double seconds = (ChronoUnit.MILLIS.between(cp1.getTimestamp(), cp2.getTimestamp())) / 1000.0;

    double measuredSpeed = (distance / seconds) / 3.6;
    if (measuredSpeed > speedLimit)
      return Optional.of(new SpeedingOffense(plateId, cp1.getCameraId(),  measuredSpeed, speedLimit,cp1.getTimestamp()));
    return Optional.empty();
  }
}

