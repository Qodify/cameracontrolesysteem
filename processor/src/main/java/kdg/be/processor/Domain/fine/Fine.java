//package kdg.be.processor.Domain.fine;
//
//import kdg.be.processor.Domain.listener.Offense;
//
//import javax.persistence.*;
//
//@Entity
//public class Fine {
//  @Id
//  @GeneratedValue
//  private int id;
//  private double bedrag;
//
//  @OneToOne(fetch = FetchType.LAZY,
//          cascade = CascadeType.ALL,
//          mappedBy = "fine")
//  private Offense listener;
//
//  public Fine(double bedrag) {
//    this.bedrag = bedrag;
//  }
//
//  public double getBedrag() {
//    return bedrag;
//  }
//
//  public void setBedrag(double bedrag) {
//    this.bedrag = bedrag;
//  }
//
//  public Offense getOffense() {
//    return listener;
//  }
//
//  public void setOffense(Offense listener) {
//    this.listener = listener;
//  }
//}
