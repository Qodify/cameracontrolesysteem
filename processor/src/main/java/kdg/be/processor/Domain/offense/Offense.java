package kdg.be.processor.Domain.offense;

import kdg.be.processor.Domain.fine.Fine;

import javax.persistence.*;
import java.time.LocalDateTime;
//
@Entity
//@Table(name = "OFFENSES")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="offense_type",discriminatorType=DiscriminatorType.STRING)
public abstract class Offense {
  @Id
  @GeneratedValue
  protected Long Id;
  protected String licenseplate;
  protected LocalDateTime timestamp;
  //

}
