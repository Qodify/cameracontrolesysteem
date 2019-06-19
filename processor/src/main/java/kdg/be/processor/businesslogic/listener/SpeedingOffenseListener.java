package kdg.be.processor.businesslogic.listener;

import be.kdg.sa.services.CameraNotFoundException;
import be.kdg.sa.services.InvalidLicensePlateException;
import be.kdg.sa.services.LicensePlateNotFoundException;
import kdg.be.processor.businesslogic.adapter.CameraServiceAdapter;
import kdg.be.processor.businesslogic.adapter.LicensePlateServiceAdapter;
import kdg.be.processor.businesslogic.service.SpeedingOffenseService;
import kdg.be.processor.domain.cameramessage.CameraMessage;
import kdg.be.processor.domain.offense.Offense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class SpeedingOffenseListener implements OffenseListener {

  private LicensePlateServiceAdapter licensePlateServiceAdapter;
  private CameraServiceAdapter cameraServiceAdapter;
  private final SpeedingOffenseService speedingOffenseService;

  @Autowired
  public SpeedingOffenseListener(LicensePlateServiceAdapter licensePlateServiceAdapter,
                                 CameraServiceAdapter cameraServiceAdapter,
                                 SpeedingOffenseService speedingOffenseService) {

    this.licensePlateServiceAdapter = licensePlateServiceAdapter;
    this.cameraServiceAdapter = cameraServiceAdapter;
    this.speedingOffenseService = speedingOffenseService;
  }

  @Override
  public Optional<Offense> onMessageReceived(CameraMessage cm) throws CameraNotFoundException,
                                                                      LicensePlateNotFoundException,
                                                                      InvalidLicensePlateException,
                                                                      IOException {
    try {
      return speedingOffenseService.calcOffense(licensePlateServiceAdapter.get(cm.getLicenseplate()),
                                                cameraServiceAdapter.get(cm.getId()));
    } catch (IOException e) {
      throw new IOException(e);
    }
  }
}