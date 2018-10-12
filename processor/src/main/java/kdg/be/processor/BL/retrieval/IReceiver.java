package kdg.be.processor.BL.retrieval;

import kdg.be.processor.Domain.cameramessage.CameraMessage;

public interface IReceiver {
  void notifyListener(CameraMessage cm);
}
