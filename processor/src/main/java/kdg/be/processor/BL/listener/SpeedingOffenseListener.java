package kdg.be.processor.BL.listener;

import be.kdg.sa.services.CameraNotFoundException;
import be.kdg.sa.services.InvalidLicensePlateException;
import be.kdg.sa.services.LicensePlateNotFoundException;
import kdg.be.processor.BL.adapter.CameraServiceAdapter;
import kdg.be.processor.BL.adapter.LicensePlateServiceAdapter;
import kdg.be.processor.BL.manager.OffenseManager;
import kdg.be.processor.Domain.cameramessage.CameraMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpeedingOffenseListener implements IReceiverListener {

  private LicensePlateServiceAdapter licensePlateServiceAdapter;
  private CameraServiceAdapter cameraServiceAdapter;
  private OffenseManager offenseManager;

  @Autowired
  public SpeedingOffenseListener(LicensePlateServiceAdapter licensePlateServiceAdapter, CameraServiceAdapter cameraServiceAdapter, OffenseManager offenseManager) {
    this.licensePlateServiceAdapter = licensePlateServiceAdapter;
    this.cameraServiceAdapter = cameraServiceAdapter;
    this.offenseManager = offenseManager;
  }
  @Override
  public void OnMessageReceived(CameraMessage cm) throws CameraNotFoundException, LicensePlateNotFoundException, InvalidLicensePlateException {

    offenseManager.checkSpeedingOffense(licensePlateServiceAdapter.get(cm.getLicenseplate()), cameraServiceAdapter.get(cm.getId()));
  }
}
