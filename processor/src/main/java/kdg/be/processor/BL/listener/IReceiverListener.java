package kdg.be.processor.BL.listener;

import kdg.be.processor.Domain.cameramessage.CameraMessage;

public interface IReceiverListener {
  void OnMessageReceived(CameraMessage cm);
}
