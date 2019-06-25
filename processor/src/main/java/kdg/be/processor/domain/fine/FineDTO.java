package kdg.be.processor.domain.fine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


public class FineDTO {

    public FineDTO() {
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    //fine
    @NotEmpty
    private int id;
    @NotEmpty
    private double amount;
    //Offense attributes
    @NotEmpty
    private String timestamp;

    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
