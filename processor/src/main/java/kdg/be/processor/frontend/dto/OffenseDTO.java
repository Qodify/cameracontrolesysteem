package kdg.be.processor.frontend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OffenseDTO {
  private long Id;
  private String licenseplate;
  private LocalDateTime timestamp;
  private int carEuronorm;
  private int emissionZoneEuronorm;
  private int speedLimit;
  private double carSpeed;
}
