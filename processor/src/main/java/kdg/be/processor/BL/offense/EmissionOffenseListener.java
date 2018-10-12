package kdg.be.processor.BL.offense;

import kdg.be.processor.BL.adapter.CameraServiceAdapter;
import kdg.be.processor.BL.adapter.LicensePlateServiceAdapter;
import kdg.be.processor.BL.manager.OffenseManager;
import kdg.be.processor.Domain.cameramessage.CameraMessage;
import kdg.be.processor.Domain.perception.LicensePlatePercept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmissionOffenseListener implements IReceiverListener {

  private LicensePlateServiceAdapter licensePlateServiceAdapter;
  private CameraServiceAdapter cameraServiceAdapter;
  private OffenseManager offenseManager;

  @Autowired
  public EmissionOffenseListener(LicensePlateServiceAdapter licensePlateServiceAdapter, CameraServiceAdapter cameraServiceAdapter, OffenseManager offenseManager) {
    this.licensePlateServiceAdapter = licensePlateServiceAdapter;
    this.cameraServiceAdapter = cameraServiceAdapter;
    this.offenseManager = offenseManager;
  }

  @Override
  public void OnMessageReceived(CameraMessage cm) {
    offenseManager.calcOffense(cm, licensePlateServiceAdapter.get(cm.getLicenseplate()), cameraServiceAdapter.get(cm.getId()));
  }
}
