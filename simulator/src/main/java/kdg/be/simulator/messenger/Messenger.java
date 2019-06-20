package kdg.be.simulator.messenger;

import kdg.be.simulator.models.CameraMessage;

import java.util.Optional;

public interface Messenger {
    void sendMessage(CameraMessage message);
}
