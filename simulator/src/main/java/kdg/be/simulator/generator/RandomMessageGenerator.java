package kdg.be.simulator.generator;

import kdg.be.simulator.models.CameraMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

public class RandomMessageGenerator implements IMessageGenerator {
    private Random r;
    private int randomIdBound;
    private StringBuilder randomLicensePlate;

    //TODO: instelbare bound voor id maar hoe om te zetten tot bean?
    //mag een javabean hier een geparametriseerde construcotr hebben?
    public RandomMessageGenerator(/*int rIdBound*/) {
        r = new Random();
        randomLicensePlate = new StringBuilder();
    }

    public void setRandomIdBound(int randomIdBound) {
        this.randomIdBound = randomIdBound;
    }

    @Override
    public CameraMessage generate() {
        randomLicensePlate
                .append(r.nextInt(9) + 1)
                .append('-')
                .append(rndChar())
                .append(rndChar())
                .append(rndChar())
                .append('-')
                .append(r.nextInt(9) + 1)
                .append(r.nextInt(9) + 1)
                .append(r.nextInt(9) + 1);
        return new CameraMessage(r.nextInt(randomIdBound),
                    randomLicensePlate.toString(), LocalDateTime.now());
    }

    public char rndChar() {
        char r_3_Char = (char) (65 + r.nextInt(25));
        return r_3_Char;
    }

    @Scheduled(fixedRate = 5000)
    public void doGenerate(){
        System.out.println(generate());

    }
}
