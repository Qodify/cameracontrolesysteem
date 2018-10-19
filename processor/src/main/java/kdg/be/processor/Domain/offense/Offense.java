package kdg.be.processor.Domain.offense;

import javax.persistence.*;

@Entity
@Table(name = "OFFENSES")
public abstract class Offense {
  @Id
  @GeneratedValue
  protected Long Id;
  protected String licenseplate;
  protected double fine;
//
//  @OneToOne(fetch = FetchType.LAZY, optional = false)
//  @JoinColumn(name = "fine_id")
//  protected Fine fine;

}
