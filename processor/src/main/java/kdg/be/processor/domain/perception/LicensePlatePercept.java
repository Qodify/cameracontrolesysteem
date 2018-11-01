package kdg.be.processor.domain.perception;

public class LicensePlatePercept {
  private String plateId;
  private String nationalNumber;
  private int euroNumber;

  public LicensePlatePercept() {
  }

  public String getPlateId() {
    return plateId;
  }

  public void setPlateId(String plateId) {
    this.plateId = plateId;
  }

  public String getNationalNumber() {
    return nationalNumber;
  }

  public void setNationalNumber(String nationalNumber) {
    this.nationalNumber = nationalNumber;
  }

  public int getEuroNumber() {
    return euroNumber;
  }

  public void setEuroNumber(int euroNumber) {
    this.euroNumber = euroNumber;
  }

  @Override
  public String toString() {
    return "LicensePlatePercept{" +
        "plateId='" + plateId + '\'' +
        ", nationalNumber='" + nationalNumber + '\'' +
        ", euroNumber=" + euroNumber +
        '}';
  }
}
