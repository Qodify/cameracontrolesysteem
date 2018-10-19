package kdg.be.processor.Domain.offense;

import kdg.be.processor.Domain.fine.Fine;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "OFFENSES")
public abstract class Offense {
  @Id
  @GeneratedValue
  protected Long Id;
  protected String licenseplate;
  protected LocalDate timestamp;
  //

}
