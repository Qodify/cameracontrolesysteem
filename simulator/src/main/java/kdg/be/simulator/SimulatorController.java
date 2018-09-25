package kdg.be.simulator;

import kdg.be.simulator.Messenger.IMessenger;
import kdg.be.simulator.generator.IMessageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

@Component
public class SimulatorController {

  @Autowired
  private IMessageGenerator messageGenerator;
  @Autowired
  private IMessenger messenger;


  public SimulatorController(IMessageGenerator messageGenerator, IMessenger messenger) {
    this.messageGenerator = messageGenerator;
    this.messenger = messenger;
  }

  @Scheduled(fixedDelayString = "${frequentie}")
  public void generate() {
    messenger.sendMessage(messageGenerator.generate());
  }


}
