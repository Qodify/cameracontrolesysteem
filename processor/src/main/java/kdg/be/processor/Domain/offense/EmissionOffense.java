package kdg.be.processor.Domain.offense;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@Entity
public class EmissionOffense extends Offense {

  private final int FINE = 80;
  @Id
  @GeneratedValue
  private Long Id;
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

  @Override
  public String toString() {
    return "EmissionOffense{" +
        "Id=" + Id +
        ", licenseplate='" + licenseplate + '\'' +
        ", carEuronorm=" + carEuronorm +
        ", emissionZoneEuronorm=" + emissionZoneEuronorm +
        '}';
  }
}
