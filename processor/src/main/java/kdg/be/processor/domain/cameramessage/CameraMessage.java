package kdg.be.processor.domain.cameramessage;


import kdg.be.processor.frontend.dto.CameraMessageDTO;

import java.time.LocalDateTime;

public class CameraMessage {
    private int id;
    private String licenseplate;
    private LocalDateTime timestamp;
    private int delay;
    private int retries;

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
        this.retries = 0;

    }

    public CameraMessage(CameraMessageDTO cmDTO) {
        this.id = cmDTO.getId();
        this.licenseplate = cmDTO.getLicenseplate();
        this.timestamp = LocalDateTime.parse(cmDTO.getTimestamp());
        this.retries = 0;

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

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getRetries() {
        return retries;
    }

    public void retry() {
        this.retries = retries + 1;
    }
}
