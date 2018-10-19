package kdg.be.simulator;

import kdg.be.simulator.controller.SimulatorController;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableRabbit
public class SimulatorApplication {



  public static void main(String[] args) {

    SpringApplication.run(SimulatorApplication.class, args);

  }
}