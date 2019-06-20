package kdg.be.simulator.generator;

import kdg.be.simulator.controller.GeneratorListener;
import kdg.be.simulator.models.CameraMessage;

import java.util.List;
import java.util.Optional;

public interface MessageGenerator {
    void generate() throws InterruptedException;

    void addListener(GeneratorListener listener);

    void notifyListeners(CameraMessage message);

}
