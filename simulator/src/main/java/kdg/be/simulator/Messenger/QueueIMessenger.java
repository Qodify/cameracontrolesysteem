package kdg.be.simulator.Messenger;

import kdg.be.simulator.models.CameraMessage;
import org.springframework.stereotype.Component;

@Component
public class QueueIMessenger implements IMessenger {
    @Override
    public void sendMessage(CameraMessage cameraMessage) {

    }
}
