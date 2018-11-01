package kdg.be.processor.businesslogic.configuration;

import be.kdg.sa.services.CameraServiceProxy;
import be.kdg.sa.services.LicensePlateServiceProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExternalServicesConfig {

  @Bean
  public CameraServiceProxy cameraServiceProxy() {
    return new CameraServiceProxy();
  }

  @Bean
  public LicensePlateServiceProxy licensePlateServiceProxy() {
    return new LicensePlateServiceProxy();
  }


}
