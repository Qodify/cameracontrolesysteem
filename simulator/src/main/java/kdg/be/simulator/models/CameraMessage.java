package kdg.be.simulator.models;

import java.time.LocalDateTime;

public class CameraMessage {
    private int id;
    private String licenseplate;
    private LocalDateTime timestamp;


    @Override
    //TODO: datum formatteren volgens dd-mm-yyyy
    public String toString() {
        return "CameraMessage{" +
                "id=" + id +
                ", licenseplate='" + licenseplate + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    public CameraMessage(int id, String licenseplate,LocalDateTime timestamp) {
        this.id = id;
        this.licenseplate = licenseplate;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicenseplate() {
        return licenseplate;
    }

    public void setLicenseplate(String licenseplate) {
        this.licenseplate = licenseplate;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
