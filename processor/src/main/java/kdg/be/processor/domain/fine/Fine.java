package kdg.be.processor.domain.fine;

import kdg.be.processor.domain.offense.Offense;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Fine {

  @Id
  @GeneratedValue
  private Long id;
  private double amount;
  private boolean approved;
  private String updateAmountMotivation;
  private LocalDateTime timestamp;


  @JoinColumn(name = "fk_offenseId")
  @OneToOne(cascade = CascadeType.ALL)
  private Offense offense;

  public Fine() {
  }

  public Fine(Offense offense, double amount, LocalDateTime timestamp) {
    this.approved = false;
    this.offense = offense;
    this.amount = amount;
    this.timestamp = timestamp;
  }

  public Fine(double amount, boolean approved, Offense offense) {
    this.amount = amount;
    this.approved = approved;
    this.offense = offense;
  }

  public Fine(double amount, String updateAmountMotivation, Offense offense) {
    this.amount = amount;
    this.updateAmountMotivation = updateAmountMotivation;
    this.offense = offense;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public boolean isApproved() {
    return approved;
  }

  public void setApproved(boolean approved) {
    this.approved = approved;
  }

  public String getUpdateAmountMotivation() {
    return updateAmountMotivation;
  }

  public void setUpdateAmountMotivation(String updateAmountMotivation) {
    this.updateAmountMotivation = updateAmountMotivation;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public Offense getOffense() {
    return offense;
  }

  public void setOffense(Offense offense) {
    this.offense = offense;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }
}
