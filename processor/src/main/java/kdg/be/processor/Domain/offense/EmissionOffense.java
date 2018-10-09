package kdg.be.processor.Domain.offense;

public class EmissionOffense extends Offense {
  private final int FINE = 80;
  private String licenseplate;
  private int carEuronorm;
  private int emissionZoneEuronorm;

  public EmissionOffense(String licenseplate, int carEuronorm, int emissionZoneEuronorm) {
    this.licenseplate = licenseplate;
    this.carEuronorm = carEuronorm;
    this.emissionZoneEuronorm = emissionZoneEuronorm;
  }

  public String getLicenseplate() {
    return licenseplate;
  }

  public void setLicenseplate(String licenseplate) {
    this.licenseplate = licenseplate;
  }

  public int getCarEuronorm() {
    return carEuronorm;
  }

  public void setCarEuronorm(int carEuronorm) {
    this.carEuronorm = carEuronorm;
  }

  public int getEmissionZoneEuronorm() {
    return emissionZoneEuronorm;
  }

  public void setEmissionZoneEuronorm(int emissionZoneEuronorm) {
    this.emissionZoneEuronorm = emissionZoneEuronorm;
  }
}
