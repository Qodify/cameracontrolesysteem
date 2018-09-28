package kdg.be.simulator.controller;

import kdg.be.simulator.messenger.IMessenger;
import kdg.be.simulator.generator.IMessageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SimulatorController {

  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  @Autowired
  private IMessageGenerator messageGenerator;

  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  @Autowired
  private IMessenger messenger;

  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  public SimulatorController(IMessageGenerator messageGenerator, IMessenger messenger) {
    this.messageGenerator = messageGenerator;
    this.messenger = messenger;
  }

  @Scheduled(fixedDelayString = "${frequentie}")
  public void generate() {
    messenger.sendMessage(messageGenerator.generate());
  }


}
