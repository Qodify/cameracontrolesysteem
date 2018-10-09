package kdg.be.processor;

import be.kdg.sa.services.CameraServiceProxy;
import kdg.be.processor.BL.adapter.CameraServiceAdapter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessorApplicationTests {
  private static final Logger LOGGER = LoggerFactory.getLogger(ProcessorApplicationTests.class);

  @Test
  public void contextLoads() {
  }

  @Test
  public void testCameraService() {
    CameraServiceProxy proxy = new CameraServiceProxy();
    try {
      Assert.assertNotNull(proxy.get(0));
    } catch (IOException e) {
      LOGGER.warn("CameraServiceProxy generates IOException");
    }

  }
}
