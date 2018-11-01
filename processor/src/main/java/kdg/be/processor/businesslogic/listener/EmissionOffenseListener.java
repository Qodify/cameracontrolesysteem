package kdg.be.processor.businesslogic.listener;

import be.kdg.sa.services.CameraNotFoundException;
import be.kdg.sa.services.InvalidLicensePlateException;
import be.kdg.sa.services.LicensePlateNotFoundException;
import kdg.be.processor.businesslogic.adapter.CameraServiceAdapter;
import kdg.be.processor.businesslogic.adapter.LicensePlateServiceAdapter;
import kdg.be.processor.businesslogic.manager.EmissionOffenseManager;
import kdg.be.processor.domain.cameramessage.CameraMessage;
import kdg.be.processor.domain.offense.Offense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class EmissionOffenseListener implements IReceiverListener {

  private LicensePlateServiceAdapter licensePlateServiceAdapter;
  private CameraServiceAdapter cameraServiceAdapter;
  private final EmissionOffenseManager offenseManager;

  @Autowired
  public EmissionOffenseListener(LicensePlateServiceAdapter licensePlateServiceAdapter, CameraServiceAdapter cameraServiceAdapter, EmissionOffenseManager offenseManager) {
    this.licensePlateServiceAdapter = licensePlateServiceAdapter;
    this.cameraServiceAdapter = cameraServiceAdapter;
    this.offenseManager = offenseManager;
  }

  @Override
  public Optional<Offense> onMessageReceived(CameraMessage cm) throws CameraNotFoundException, LicensePlateNotFoundException, InvalidLicensePlateException, IOException {
    try {
      return offenseManager.calcOffense(licensePlateServiceAdapter.get(cm.getLicenseplate()), cameraServiceAdapter.get(cm.getId()));
    } catch (IOException e) {
      throw new IOException(e);
    }
  }
}
