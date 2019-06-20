package kdg.be.simulator.controller;

import kdg.be.simulator.messenger.Messenger;
import kdg.be.simulator.generator.MessageGenerator;
import kdg.be.simulator.models.CameraMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SimulatorController implements GeneratorListener {

    @Autowired
    private MessageGenerator generator;
    @Autowired
    private Messenger messenger;

    private static final Logger LOGGER = LoggerFactory.getLogger(SimulatorController.class);

    //@Autowired
    //@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @PostConstruct
    public void startGenerating() {
        generator.addListener(this);
        try {
            generator.generate();
        } catch (InterruptedException e) {
            LOGGER.warn("Thread.sleep() threw an exception: " + e.getMessage());
        }
    }


    @Override
    public void onMessageGenerated(CameraMessage cameraMessage) {
        messenger.sendMessage(cameraMessage);

    }
}
