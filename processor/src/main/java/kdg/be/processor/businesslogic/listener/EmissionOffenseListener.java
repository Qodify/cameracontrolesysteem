package kdg.be.processor.businesslogic.listener;

import be.kdg.sa.services.CameraNotFoundException;
import be.kdg.sa.services.InvalidLicensePlateException;
import be.kdg.sa.services.LicensePlateNotFoundException;
import kdg.be.processor.businesslogic.adapter.CameraServiceAdapter;
import kdg.be.processor.businesslogic.adapter.LicensePlateServiceAdapter;
import kdg.be.processor.businesslogic.service.EmissionOffenseService;
import kdg.be.processor.domain.cameramessage.CameraMessage;
import kdg.be.processor.domain.offense.Offense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class EmissionOffenseListener implements OffenseListener {

  private LicensePlateServiceAdapter licensePlateServiceAdapter;
  private CameraServiceAdapter cameraServiceAdapter;
  private final EmissionOffenseService emissionOffenseService;

  @Autowired
  public EmissionOffenseListener(LicensePlateServiceAdapter licensePlateServiceAdapter,
                                 CameraServiceAdapter cameraServiceAdapter,
                                 EmissionOffenseService emissionOffenseService) {
    this.licensePlateServiceAdapter = licensePlateServiceAdapter;
    this.cameraServiceAdapter = cameraServiceAdapter;
    this.emissionOffenseService = emissionOffenseService;
  }

  @Override
  public Optional<Offense> onMessageReceived(CameraMessage cm) throws CameraNotFoundException,
                                                                      LicensePlateNotFoundException,
                                                                      InvalidLicensePlateException,
                                                                      IOException {

    try {
      return emissionOffenseService.calcOffense(licensePlateServiceAdapter.get(cm.getLicenseplate()),
                                                cameraServiceAdapter.get(cm.getId()));
    } catch (IOException e) {
      throw new IOException(e);
    }
  }
}
