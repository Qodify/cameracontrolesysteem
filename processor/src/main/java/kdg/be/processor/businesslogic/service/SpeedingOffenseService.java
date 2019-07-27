package kdg.be.processor.businesslogic.service;

import be.kdg.sa.services.CameraNotFoundException;
import be.kdg.sa.services.InvalidLicensePlateException;
import be.kdg.sa.services.LicensePlateNotFoundException;
import kdg.be.processor.domain.offense.Offense;
import kdg.be.processor.domain.offense.SpeedingOffense;
import kdg.be.processor.domain.perception.CameraPercept;
import kdg.be.processor.domain.perception.LicensePlatePercept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.*;

@Component
public class SpeedingOffenseService implements OffenseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpeedingOffenseService.class);
    private Map<String, CameraPercept> cameraMessages;

    @Autowired
    public SpeedingOffenseService() {
        cameraMessages = new HashMap<>();
    }

    public Optional<Offense> calcOffense(Optional<LicensePlatePercept> lpp, Optional<CameraPercept> cp) {
        try {
            CameraPercept cameraPercept = cp.orElseThrow(IllegalStateException::new);

            String plateId = lpp.orElseThrow(IllegalStateException::new).getPlateId();

            if (cameraMessages.containsKey(plateId)) {

                Offense o = calcSpeedingOffense(cameraMessages.remove(plateId), cp.get(), plateId);

                if (o != null) return Optional.of(o);
            } else if (cameraPercept.getSpeedLimit() != 0) {
                cameraMessages.put(plateId, cameraPercept);
            }

        } catch (LicensePlateNotFoundException | InvalidLicensePlateException | IllegalStateException
                | CameraNotFoundException | InvalidDataAccessApiUsageException e) {
            LOGGER.warn(e.getMessage());
        }
        return Optional.empty();
    }


    private Offense calcSpeedingOffense(CameraPercept cp1, CameraPercept cp2, String plateId) {
        double speedLimit = cp1.getSpeedLimit();
        double distance = cp1.getDistance();
        double seconds = (Duration.between(cp1.getTimestamp(), cp2.getTimestamp()).toMillis()) / 1000.0;

        double measuredSpeed = (distance / seconds) / 3.6;
        if (measuredSpeed > speedLimit)
            return new SpeedingOffense(plateId, cp1.getCameraId(), cp2.getCameraId(), measuredSpeed, speedLimit, cp1.getTimestamp());
        //null gets handled in same class
        return null;
    }
}
