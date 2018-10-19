package kdg.be.simulator.controller;

import kdg.be.simulator.messenger.IMessenger;
import kdg.be.simulator.generator.IMessageGenerator;
import kdg.be.simulator.messenger.QueueMessenger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SimulatorController {

  private IMessageGenerator messageGenerator;
  private IMessenger messenger;
  private static final Logger LOGGER = LoggerFactory.getLogger(SimulatorController.class);

  @Autowired
  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  public SimulatorController(IMessageGenerator messageGenerator, IMessenger messenger) {
    this.messageGenerator = messageGenerator;
    this.messenger = messenger;
  }

  @PostConstruct
  private void generate() {
    messenger.sendMessage(messageGenerator.generate());
  }

}
