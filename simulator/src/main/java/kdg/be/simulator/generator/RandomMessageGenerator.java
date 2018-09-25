package kdg.be.simulator.generator;

import kdg.be.simulator.models.CameraMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
@ConditionalOnProperty(name = "MessageGeneratorType", havingValue = "random")
public class RandomMessageGenerator implements IMessageGenerator {
  private Random r;
  private int randomIdBound;
  private String randomLicensePlate;

  //TODO: instelbare bound voor id maar hoe om te zetten tot bean?
  //mag een javabean hier een geparametriseerde construcotr hebben?
  public RandomMessageGenerator(@Value("${rIdbound:1000}") Integer rIdBound) {
    r = new Random();
    randomIdBound = rIdBound;
  }

  //TODO: moet een cameramessage deel zijn van een thread, of het geheel van cameramessages een algoritme
  @Override
  public CameraMessage generate() {
    randomLicensePlate = String.format("%d-%s%s%s-%d%d%d", r.nextInt(9) + 1, rndChar(), rndChar(), rndChar(),
        r.nextInt(9) + 1, r.nextInt(9) + 1, r.nextInt(9) + 1);
    System.out.println(new CameraMessage(randomIdBound, randomLicensePlate.toString(), LocalDateTime.now()));

    return new CameraMessage(randomIdBound, randomLicensePlate.toString(), LocalDateTime.now());
  }

  private char rndChar() {
    return (char) (65 + r.nextInt(25));
  }


}
