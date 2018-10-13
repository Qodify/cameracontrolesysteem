package kdg.be.processor.Domain.offense;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//properder dan later een enum aan te passen
@Entity
public abstract class Offense {
  @Id
  @GeneratedValue
  private Long Id;
  private double bedrag;
}
