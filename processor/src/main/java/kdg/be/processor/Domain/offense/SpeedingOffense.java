package kdg.be.processor.Domain.offense;


import javax.persistence.*;

@Entity(name = "speeding_offenses")
@DiscriminatorValue("S")

public class SpeedingOffense extends Offense {

  private int speedLimit;
  private double carSpeed;

  public SpeedingOffense(String licenseplate, int speedLimit, double carSpeed) {
    this.licenseplate = licenseplate;
    this.speedLimit = speedLimit;
    this.carSpeed = carSpeed;
  }

  public int getSpeedLimit() {
    return speedLimit;
  }

  public void setSpeedLimit(int speedLimit) {
    this.speedLimit = speedLimit;
  }

  public double getCarSpeed() {
    return carSpeed;
  }

  public void setCarSpeed(double carSpeed) {
    this.carSpeed = carSpeed;
  }
}
