package kdg.be.simulator.generator;

import kdg.be.simulator.generator.FileGenerator;
import kdg.be.simulator.generator.IMessageGenerator;
import kdg.be.simulator.generator.RandomMessageGenerator;
import kdg.be.simulator.models.CameraMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration //voor high level configuratie van je applicatie kan je deze klasse een interface implementeren
public class GeneratorConfig {
    //@Bean("fileGNR") //de naam van deze bean is default de naam van deze methode
    //public MessageGenerator fileGenerator() {
    //return new FileGenerator();
    //}

}
