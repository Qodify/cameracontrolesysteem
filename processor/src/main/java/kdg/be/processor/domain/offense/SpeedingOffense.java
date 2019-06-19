package kdg.be.processor.domain.offense;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "speeding_offenses")
@DiscriminatorValue("S")

public class SpeedingOffense extends Offense {


    private double speedLimit;
    private double measuredSpeed;
    private LocalDateTime timestamp;
    private int cameraId1;
    private int cameraId2;

    public SpeedingOffense(String licenseplate, int cameraId1, int cameraId2, double measuredSpeed, double speedLimit, LocalDateTime timestamp) {
        this.cameraId2 = cameraId2;
        this.licenseplate = licenseplate;
        this.cameraId1 = cameraId1;
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
