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

    @ConditionalOnProperty(name = "MessageGeneratorType", havingValue = "random")
    @Bean
    public IMessageGenerator randomMessageGenerator() {
        RandomMessageGenerator randomMessageGenerator = new RandomMessageGenerator();
        randomMessageGenerator.setRandomIdBound(1000);
        System.out.println("rmg");
        return randomMessageGenerator;
    }

    @ConditionalOnProperty(name = "MessageGeneratorType", havingValue = "file")
    @Bean
    public IMessageGenerator fileGenerator() {
        System.out.println("fg");
        return new FileGenerator();
    }
}
