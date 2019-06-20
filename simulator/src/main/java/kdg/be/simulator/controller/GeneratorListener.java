package kdg.be.simulator.controller;

import kdg.be.simulator.models.CameraMessage;

public interface GeneratorListener {
    void onMessageGenerated(CameraMessage cameraMessage);

}
