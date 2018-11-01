package kdg.be.processor.domain.cameramessage;


import lombok.Data;

@Data
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

}
