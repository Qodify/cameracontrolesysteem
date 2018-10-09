package kdg.be.processor.BL.listener;

import kdg.be.processor.BL.adapter.LicensePlateServiceAdapter;
import kdg.be.processor.BL.offense.EmissionOffenseCalculator;
import kdg.be.processor.BL.offense.SpeedingOffenseCalculator;
import kdg.be.processor.Domain.cameramessage.CameraMessage;
import kdg.be.processor.BL.adapter.CameraServiceAdapter;
import kdg.be.processor.Domain.offense.EmissionOffense;
import kdg.be.processor.Domain.offense.Offense;
import kdg.be.processor.Domain.perception.CameraPercept;
import kdg.be.processor.Domain.perception.LicensePlatePercept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OffenseController {


  private CameraServiceAdapter cameraServiceAdapter;
  private LicensePlateServiceAdapter licensePlateServiceAdapter;
  private EmissionOffenseCalculator emissionOffenseCalculator;
  private SpeedingOffenseCalculator speedingOffenseCalculator;

  @Autowired
  public OffenseController(CameraServiceAdapter cameraServiceAdapter, LicensePlateServiceAdapter licensePlateServiceAdapter,
                           EmissionOffenseCalculator emissionOffenseCalculator, SpeedingOffenseCalculator speedingOffenseCalculator) {
    this.cameraServiceAdapter = cameraServiceAdapter;
    this.licensePlateServiceAdapter = licensePlateServiceAdapter;
    this.speedingOffenseCalculator = speedingOffenseCalculator;
    this.emissionOffenseCalculator = emissionOffenseCalculator;
  }


  public void MessageHandler(CameraMessage cm) {
    var cp = cameraServiceAdapter.get(cm.getId());
    var lpp = licensePlateServiceAdapter.get(cm.getLicenseplate());
//    emissionOffenseCalculator.calculateFine(cp,lpp);
//    if (cp != null && lpp != null) offenseCalculator(cp, lpp);

  }
//
//  public Offense offenseCalculator(CameraPercept cp, LicensePlatePercept lpp) {
//    if (cp.getEuroNorm() < lpp.getEuroNumber()) {
//      return new EmissionOffense(lpp.getPlateId(), cp.getEuroNorm(), lpp.getEuroNumber());
//    }
//
//    return null;
//  }
}
