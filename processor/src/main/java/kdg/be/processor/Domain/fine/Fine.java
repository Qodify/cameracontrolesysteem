package kdg.be.processor.Domain.fine;

import kdg.be.processor.Domain.offense.Offense;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Fine {

  @Id
  @GeneratedValue
  private int id;
  private double bedrag;

  @JoinColumn(name = "fk_offenseId")
  @OneToOne(cascade = CascadeType.ALL)
  private Offense offense;


  public Fine(Offense offense , double bedrag) {
    this.offense = offense;
    this.bedrag = bedrag;
  }

  public double getBedrag() {
    return bedrag;
  }

  public void setBedrag(double bedrag) {
    this.bedrag = bedrag;
  }

  public Offense getOffense() {
    return offense;
  }

  public void setOffense(Offense offense) {
    this.offense = offense;
  }
}
