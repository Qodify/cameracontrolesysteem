package kdg.be.processor.Domain.offense;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class SpeedingOffense extends Offense {

  private int speedLimit;
  private double carSpeed;

  public SpeedingOffense(String licenseplate, int speedLimit, double carSpeed) {
    this.licenseplate = licenseplate;
    this.speedLimit = speedLimit;
    this.carSpeed = carSpeed;

  }
}
