package kdg.be.processor.Domain.offense;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class EmissionOffense extends Offense {

//  private int id = 0;
  private int carEuronorm;
  private int emissionZoneEuronorm;


  public EmissionOffense(String licenseplate, int carEuronorm, int emissionZoneEuronorm) {
//    this.id++;
    this.licenseplate = licenseplate;
    this.carEuronorm = carEuronorm;
    this.emissionZoneEuronorm = emissionZoneEuronorm;
  }

//  public int getId() {
//    return id;
//  }
//
//  public void setId(int id) {
//    this.id = id;
//  }

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
            "Id=" + super.Id +
            ", licenseplate='" + licenseplate + '\'' +
            ", carEuronorm=" + carEuronorm +
            ", emissionZoneEuronorm=" + emissionZoneEuronorm +
            '}';
  }
}
