package kdg.be.processor.businesslogic.adapter;

import be.kdg.sa.services.InvalidLicensePlateException;
import be.kdg.sa.services.LicensePlateNotFoundException;
import be.kdg.sa.services.LicensePlateServiceProxy;
import com.fasterxml.jackson.databind.ObjectMapper;
import kdg.be.processor.domain.perception.LicensePlatePercept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class LicensePlateServiceAdapter {
  private static final Logger LOGGER = LoggerFactory.getLogger(CameraServiceAdapter.class);

  private LicensePlateServiceProxy licensePlateServiceProxy;

  @Autowired
  public LicensePlateServiceAdapter(LicensePlateServiceProxy licensePlateServiceProxy) {
    this.licensePlateServiceProxy = licensePlateServiceProxy;
  }

  public Optional<LicensePlatePercept> get(String licensePLate) throws InvalidLicensePlateException, LicensePlateNotFoundException {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      LicensePlatePercept lpp = objectMapper.readValue(licensePlateServiceProxy.get(licensePLate), LicensePlatePercept.class);
      LOGGER.trace(lpp.toString());
      return Optional.of(lpp);
    } catch (IOException e) {
      LOGGER.warn(e.getMessage());
    }
    return Optional.empty();
  }
}
