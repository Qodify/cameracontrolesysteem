package kdg.be.processor.Domain.fine;

import kdg.be.processor.Domain.offense.OffenseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FineDTO {
  //fine
  @NotEmpty
  private int id;
  @NotEmpty
  private double amount;
  //Offense attributes
  @NotEmpty
  private String timestamp;

}
