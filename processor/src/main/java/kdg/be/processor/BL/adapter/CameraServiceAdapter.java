package kdg.be.processor.BL.adapter;

import be.kdg.sa.services.CameraNotFoundException;
import be.kdg.sa.services.CameraServiceProxy;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import kdg.be.processor.Domain.perception.CameraPercept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class CameraServiceAdapter {
  private static final Logger LOGGER = LoggerFactory.getLogger(CameraServiceAdapter.class);

  private CameraServiceProxy cameraServiceProxy;

  @Autowired
  public CameraServiceAdapter(CameraServiceProxy cameraServiceProxy) {
    this.cameraServiceProxy = cameraServiceProxy;
  }


  public Optional<CameraPercept> get(int id) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      SimpleModule module = new SimpleModule("CustomCameraDeserializer");
      module.addDeserializer(CameraPercept.class, new CustomCameraDeserializer());
      objectMapper.registerModule(module);
      return Optional.of(objectMapper.readValue(cameraServiceProxy.get(id), CameraPercept.class));
    } catch (CameraNotFoundException e) {
      throw new CameraNotFoundException(id);

    } catch (IOException e) {
      LOGGER.error(e.getMessage());
    }
    return Optional.empty();
  }

  class CustomCameraDeserializer extends StdDeserializer<CameraPercept> {
    private CustomCameraDeserializer() {
      this(null);
    }

    private CustomCameraDeserializer(Class<?> vc) {
      super(vc);
    }

    @Override
    public CameraPercept deserialize(JsonParser parser, DeserializationContext deserializer) {
      CameraPercept cp = new CameraPercept();
      ObjectCodec codec = parser.getCodec();
      JsonNode node = null;
      try {
        node = codec.readTree(parser);
      } catch (IOException e) {
        LOGGER.error(e.getMessage());
      }

      JsonNode cameraIdNode = node.get("cameraId");
      int cameraId = cameraIdNode.asInt();
      cp.setCameraId(cameraId);

      JsonNode euroNormNode = node.get("euroNorm");
      if (euroNormNode != null) {
        int euroNorm = euroNormNode.asInt();
        cp.setEuroNorm(euroNorm);
      } else cp.setEuroNorm(0);

      JsonNode segmentNode = node.get("segment");
      if (segmentNode != null) {
        JsonNode connectedCameraIdNode = segmentNode.get("connectedCameraId");
        cp.setConnectedCameraId(connectedCameraIdNode.asInt());
        JsonNode distanceNode = segmentNode.get("distance");
        cp.setDistance(distanceNode.asDouble());
        JsonNode speedLimitNode = segmentNode.get("speedLimit");
        cp.setSpeedLimit(speedLimitNode.asDouble());
      }

      JsonNode locationsNode = node.get("location");
      if (locationsNode != null) {
        JsonNode latNode = locationsNode.get("lat");
        cp.setLatitude(latNode.asDouble());
        JsonNode longNode = locationsNode.get("long");
        cp.setLongitude(longNode.asDouble());
        System.out.println(cp);
      }

      return cp;
    }
  }


}
