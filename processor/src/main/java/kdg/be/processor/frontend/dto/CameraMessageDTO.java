package kdg.be.processor.frontend.dto;


import kdg.be.processor.domain.cameramessage.CameraMessage;
import lombok.Data;


public class CameraMessageDTO {
  private int id;
  private String licenseplate;
  private String timestamp;

  public CameraMessageDTO(CameraMessage cm) {
    this.id = cm.getId();
    this.licenseplate = cm.getLicenseplate();
    this.timestamp = cm.getTimestamp().toString();
  }

  public CameraMessageDTO(int id, String licenseplate, String timestamp) {
    this.id = id;
    this.licenseplate = licenseplate;
    this.timestamp = timestamp;
  }

  public CameraMessageDTO() {

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
