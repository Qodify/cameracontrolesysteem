package kdg.be.simulator.Messenger;

import kdg.be.simulator.generator.IMessageGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "MessengerType", havingValue = "CommandLineMessenger")
public class CommandLineMessenger implements IMessenger {

  private final IMessageGenerator messageGenerator;  //constructorInjection in combinatie van de test. Nu is autowired niet meer nodig
  //waarom construcotrinjection ipv fieldinjection? blij dat je het vraagt.
  //je kan dit ook met @autowired doen en final weglaten maar wordt op neergekeken want docuementatiegeneratie
  //mist de dependencyinjection

  public CommandLineMessenger( IMessageGenerator messageGenerator) {
    this.messageGenerator = messageGenerator;
  }


  @Scheduled(fixedDelayString = "${frequentie}")
  @Override
  public void sendMessage() {
    messageGenerator.generate();
  }

}
