package kdg.be.simulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SimulatorApplication {
    //2 bean fail oplossen
    // @Qualifier:
    //boven de bean een extra @ConditionalOnProperty is in configfile te doen

    public static void main(String[] args) {
//        var ctx = new AnnotationConfigApplicationContext();
//        ctx.register(GeneratorConfig.class);


        SpringApplication.run(SimulatorApplication.class, args);
    }
}
