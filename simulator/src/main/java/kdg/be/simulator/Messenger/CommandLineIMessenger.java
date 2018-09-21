package kdg.be.simulator.Messenger;

import kdg.be.simulator.generator.IMessageGenerator;
import kdg.be.simulator.generator.RandomMessageGenerator;
import kdg.be.simulator.models.CameraMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CommandLineIMessenger implements IMessenger {


    private final IMessageGenerator messageGenerator;  //constructorInjection in combinatie van de test. Nu is autowired niet meer nodig
    //waarom construcotrinjection ipv fieldinjection? blij dat je het vraagt.
    //je kan dit ook met @autowired doen en final weglaten maar wordt op neergekeken want docuementatiegeneratie
    //mist de dependencyinjection

    @Autowired
    public CommandLineIMessenger(@Qualifier("fileGenerator") IMessageGenerator messageGenerator) {
        this.messageGenerator = messageGenerator;
    }



    @Override
    public void sendMessage(CameraMessage cameraMessage) {
        System.out.println();
    }
}
