package kdg.be.simulator.generator;

import kdg.be.simulator.controller.GeneratorListener;
import kdg.be.simulator.models.CameraMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
@ConditionalOnProperty(name = "MessageGeneratorType", havingValue = "random")
public class RandomMessageGenerator implements MessageGenerator {

    private Random r;
    @Value("${rIdbound:1000}")
    private Integer rIdBound;
    private int cameraIdBound;
    private List<GeneratorListener> listeners;

    private static final Logger LOGGER = LoggerFactory.getLogger(RandomMessageGenerator.class);

    public RandomMessageGenerator(@Value("${cameraIdBound:1000}") Integer cameraIdBound) {
        r = new Random();
        this.cameraIdBound = cameraIdBound;
        listeners = new LinkedList<>();


    }

    @Override
    public void generate() throws InterruptedException {
        LOGGER.debug("generate message");
        String randomLicensePlate = String.format("%d-%s%s%s-%d%d%d",
                r.nextInt(9) + 1, rndChar(), rndChar(), rndChar(),
                r.nextInt(9) + 1, r.nextInt(9) + 1, r.nextInt(9) + 1);
        notifyListeners(new CameraMessage(r.nextInt(cameraIdBound) + 1, randomLicensePlate, LocalDateTime.now()));
        Thread.sleep(r.nextInt(3000));
        generate();

    }

    private char rndChar() {
        return (char) (65 + r.nextInt(25));
    }


    @Override
    public void addListener(GeneratorListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void notifyListeners(CameraMessage message) {
        listeners.forEach(listener -> listener.onMessageGenerated(message));

    }


}
