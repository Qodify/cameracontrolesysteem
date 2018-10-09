package kdg.be.processor.BL.adapter;

import be.kdg.sa.services.InvalidLicensePlateException;
import be.kdg.sa.services.LicensePlateNotFoundException;
import be.kdg.sa.services.LicensePlateServiceProxy;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import kdg.be.processor.Domain.perception.CameraPercept;
import kdg.be.processor.Domain.perception.LicensePlatePercept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LicensePlateServiceAdapter {
  private static final Logger LOGGER = LoggerFactory.getLogger(CameraServiceAdapter.class);

  private LicensePlateServiceProxy licensePlateServiceProxy;

  public LicensePlateServiceAdapter() {
    licensePlateServiceProxy = new LicensePlateServiceProxy();
  }

  public LicensePlatePercept get(String licensePLate) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();

      LicensePlatePercept lpp = objectMapper.readValue(licensePlateServiceProxy.get(licensePLate), LicensePlatePercept.class);
      LOGGER.trace(lpp.toString());
      return lpp;
    } catch (IOException | InvalidLicensePlateException | LicensePlateNotFoundException e) {
      e.getMessage();
    }
    return null;
  }
}
