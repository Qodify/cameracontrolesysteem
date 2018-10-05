package kdg.be.simulator.models;

import java.time.LocalDateTime;

public class CameraMessageDTO {
  private int id;
  private String licenseplate;
  private String timestamp;
  public CameraMessageDTO(CameraMessage cm) {
    this.id = cm.getId();
    this.licenseplate = cm.getLicenseplate();
    this.timestamp = cm.getLicenseplate().toString();
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

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }
}
