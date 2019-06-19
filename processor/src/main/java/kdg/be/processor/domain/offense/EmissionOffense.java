package kdg.be.processor.domain.offense;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity(name = "emission_offenses")
@DiscriminatorValue("E")
public class EmissionOffense extends Offense {


  private int carEuronorm;
  private int emissionZoneEuronorm;
  private String NationalRegisterIdNumber;


  public EmissionOffense(LocalDateTime timestamp, String licenseplate, int carEuronorm, int emissionZoneEuronorm, String nationalRegisterIdNumber) {
    this.NationalRegisterIdNumber = nationalRegisterIdNumber;
    this.timestamp = timestamp;
    this.licenseplate = licenseplate;
    this.carEuronorm = carEuronorm;
    this.emissionZoneEuronorm = emissionZoneEuronorm;
  }

  public LocalDateTime getTimestamp() {
    return super.timestamp;
  }
  public void setTimestamp(LocalDateTime timestamp){
    this.timestamp = timestamp;
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
            "carEuronorm=" + carEuronorm +
            ", emissionZoneEuronorm=" + emissionZoneEuronorm +
            ", Id=" + Id +
            ", licenseplate='" + licenseplate + '\'' +
            ", timestamp=" + timestamp +
            "}\n";
  }

  public String getNationalRegisterIdNumber() {
    return NationalRegisterIdNumber;
  }

  public void setNationalRegisterIdNumber(String nationalRegisterIdNumber) {
    NationalRegisterIdNumber = nationalRegisterIdNumber;
  }
}
