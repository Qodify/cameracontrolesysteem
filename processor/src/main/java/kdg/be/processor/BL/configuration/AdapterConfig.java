package kdg.be.processor.BL.configuration;

import be.kdg.sa.services.CameraServiceProxy;
import be.kdg.sa.services.LicensePlateServiceProxy;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterConfig {

  @Bean
  public CameraServiceProxy cameraServiceProxy() {
    return new CameraServiceProxy();
  }

  @Bean
  public LicensePlateServiceProxy licensePlateServiceProxy() {
    return new LicensePlateServiceProxy();
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
