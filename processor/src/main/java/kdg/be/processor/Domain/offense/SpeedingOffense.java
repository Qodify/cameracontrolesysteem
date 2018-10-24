package kdg.be.processor.Domain.offense;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "speeding_offenses")
@DiscriminatorValue("S")

public class SpeedingOffense extends Offense {


  private double speedLimit;
  private double measuredSpeed;
  private LocalDateTime timestamp;
  private int cameraId;

  public SpeedingOffense(String licenseplate, int cameraId, double measuredSpeed, double speedLimit, LocalDateTime timestamp) {
    this.licenseplate = licenseplate;
    this.cameraId = cameraId;
    this.speedLimit = speedLimit;
    this.measuredSpeed = measuredSpeed;
    this.timestamp = timestamp;

  }

  public double getSpeedLimit() {
    return speedLimit;
  }

  public void setSpeedLimit(int speedLimit) {
    this.speedLimit = speedLimit;
  }

  public double getCarSpeed() {
    return measuredSpeed;
  }

  public void setCarSpeed(double measuredSpeed) {
    this.measuredSpeed = measuredSpeed;
  }
}
