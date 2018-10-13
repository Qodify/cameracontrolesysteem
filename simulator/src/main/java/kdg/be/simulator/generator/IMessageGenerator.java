package kdg.be.simulator.generator;

import kdg.be.simulator.models.CameraMessage;

import java.util.List;
import java.util.Optional;

public interface IMessageGenerator {
  Optional<CameraMessage> generate();


}
