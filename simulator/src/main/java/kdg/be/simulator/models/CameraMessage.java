package kdg.be.simulator.models;



import java.time.LocalDateTime;

public class CameraMessage {
  private int id;
  private String licenseplate;
  private LocalDateTime timestamp;
  private int delay;


//  @Override
//  public String toString() {
//    Random r = new Random();
//
//    return
//        id +
//            "," + licenseplate +
//            "," + r.nextInt(4999) + 1;
//  }

  @Override
  //TODO: datum formatteren volgens dd-mm-yyyy
  public String toString() {
    return "CameraMessage{" +
        "id: " + id +
        ", licenseplate: '" + licenseplate + '\'' +
        ", timestamp: " + timestamp +
        '}';
  }


  public CameraMessage(int id, String licenseplate, LocalDateTime timestamp) {
    this.id = id;
    this.licenseplate = licenseplate;
    this.timestamp = timestamp;
  }


  public int getId() {
    return id;
  }

  public String getLicenseplate() {
    return licenseplate;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

}
