package kdg.be.processor.BL.manager;

import kdg.be.processor.BL.adapter.CameraServiceAdapter;
import kdg.be.processor.BL.adapter.LicensePlateServiceAdapter;
import kdg.be.processor.Domain.cameramessage.CameraMessage;
import kdg.be.processor.Domain.perception.CameraPercept;
import kdg.be.processor.Domain.perception.LicensePlatePercept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OffenseManager {

  private FineManager fineManager;

  @Autowired
  public OffenseManager(FineManager fineManager) {
    this.fineManager = fineManager;
  }

  public void calcOffense(CameraMessage cm, LicensePlatePercept lpp, CameraPercept cp) {
    if (lpp.getEuroNumber() < cp.getEuroNorm()) {
      fineManager.createFine();
    }
  }
}
