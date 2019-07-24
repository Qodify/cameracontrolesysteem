package kdg.be.processor.domain.fine;

import kdg.be.processor.domain.offense.Offense;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


public class FineDTO {
    private Long id;
    private double amount;
    private Offense offense;
    private String motivation;
    private Boolean approved;

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public FineDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Offense getOffense() {
        return offense;
    }

    public void setOffense(Offense offense) {
        this.offense = offense;
    }

    public FineDTO(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "FineDTO{" +
                "id=" + id +
                ", amount=" + amount +
                ", offense=" + offense +
                ", motivation='" + motivation + '\'' +
                ", approved=" + approved +
                '}';
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getJustification() {
        return motivation;
    }

    public void setJustification(String motivation) {
        this.motivation = motivation;
    }

    public Boolean getIsApproved() {
        return approved;
    }

    public void setIsApproved(Boolean approved) {
        this.approved = approved;
    }
}
