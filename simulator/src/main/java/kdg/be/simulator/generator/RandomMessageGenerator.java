package kdg.be.simulator.generator;

import kdg.be.simulator.models.CameraMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
@ConditionalOnProperty(name = "MessageGeneratorType", havingValue = "random")
public class RandomMessageGenerator implements IMessageGenerator {

  private Random r;
  @Value("${rIdbound:1000}")
  Integer rIdBound;
  private int randomIdBound;
  private String randomLicensePlate;
  private static final Logger LOGGER = LoggerFactory.getLogger(RandomMessageGenerator.class);

  public RandomMessageGenerator(@Value("${rIdbound:1000}") Integer rIdBound) {
    r = new Random();
    this.randomIdBound = rIdBound;
  }

  public int getRandomIdBound() {
    return randomIdBound;
  }

  public void setRandomIdBound(int randomIdBound) {
    this.randomIdBound = randomIdBound;
  }

  public String getRandomLicensePlate() {
    return randomLicensePlate;
  }

  public void setRandomLicensePlate(String randomLicensePlate) {
    this.randomLicensePlate = randomLicensePlate;
  }

  @Override
  public CameraMessage generate() {
    LOGGER.debug("generate message");
    randomLicensePlate = String.format("%d-%s%s%s-%d%d%d",
        r.nextInt(9) + 1, rndChar(), rndChar(), rndChar(),
        r.nextInt(9) + 1, r.nextInt(9) + 1, r.nextInt(9) + 1);

    return new CameraMessage(r.nextInt(randomIdBound) + 1, randomLicensePlate, LocalDateTime.now());
  }

  private char rndChar() {
    return (char) (65 + r.nextInt(25));
  }
  
}
