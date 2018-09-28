package kdg.be.simulator.messenger;

import kdg.be.simulator.models.CameraMessage;

public interface IMessenger {
    void sendMessage(CameraMessage message);
}
