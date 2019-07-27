package kdg.be.processor.frontend.dto;

import java.util.HashMap;
import java.util.Map;

public class PropertiesDTO {

    public Map<String, String> prop = new HashMap<>();

    public PropertiesDTO() {
    }

    public PropertiesDTO(HashMap<String, String> all) {
        prop = all;
    }

    public void setProp(Map<String, String> prop) {
        this.prop = prop;
    }

    public Map<String, String> getProp() {
        return prop;
    }
}
