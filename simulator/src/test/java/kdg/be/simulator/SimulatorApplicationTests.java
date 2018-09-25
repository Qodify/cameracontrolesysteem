package kdg.be.simulator;

import kdg.be.simulator.generator.IMessageGenerator;
import kdg.be.simulator.models.CameraMessage;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class) //container moet gestart worden met test
@SpringBootTest
public class SimulatorApplicationTests {


    @Autowired //dit is FieldInjection:
    private IMessageGenerator messageGenerator;

    @Test
    public void testMessageGenerator(){
        CameraMessage cameraMessage = messageGenerator.generate();
//        Assert.assertTrue(cameraMessage.getLicenseplate().matches("^[0-9]-[A-Z]{3}-[0-9]{3}"));
        assertThat(cameraMessage.getLicenseplate()).matches("^[0-9]-[A-Z]{3}-[0-9]{3}");
    }


}
