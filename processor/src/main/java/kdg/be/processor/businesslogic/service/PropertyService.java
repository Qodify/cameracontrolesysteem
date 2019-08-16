package kdg.be.processor.businesslogic.service;

import kdg.be.processor.domain.Property;
import kdg.be.processor.frontend.exception.UnPersistableException;
import kdg.be.processor.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    //CREATE
    public Property save(Property property) throws UnPersistableException {
        try {
            return propertyRepository.save(property);
        } catch (DataAccessException dae) {
            throw new UnPersistableException("Unable to save");
        }
    }

    //CREATE
    public void save(Map<String, String> prop) throws UnPersistableException {
        for (Map.Entry<String, String> entry : prop.entrySet()) {
            save(new Property(entry.getKey(), entry.getValue()));
        }
    }

    //READ
    public String get(String prop) {
        return propertyRepository.getOne(prop).getValue();
    }

    //READ
    public HashMap<String, String> getAll() {
        HashMap<String, String> map = new HashMap<>();
        for (Property p : propertyRepository.findAll())
            map.put(p.getProperty(), p.getValue());
        return map;
    }
}