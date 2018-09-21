package kdg.be.simulator;

import kdg.be.simulator.generator.FileGenerator;
import kdg.be.simulator.generator.IMessageGenerator;
import kdg.be.simulator.generator.RandomMessageGenerator;
import kdg.be.simulator.models.CameraMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //voor high level configuratie van je applicatie kan je deze klasse een interface implementeren
public class GeneratorConfig {
    //@Bean("fileGNR") //de naam van deze bean is default de naam van deze methode
    //public MessageGenerator fileGenerator() {
    //return new FileGenerator();
    //}

    @Bean
    @Qualifier
    IMessageGenerator randomMessageGenerator() {
        RandomMessageGenerator randomMessageGenerator = new RandomMessageGenerator();
        randomMessageGenerator.setRandomIdBound(1000);
        return randomMessageGenerator;
    }

    @Bean
    IMessageGenerator fileGenerator() {
        return new FileGenerator();
    }
}
