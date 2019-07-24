package kdg.be.processor.businesslogic.service;

import kdg.be.processor.domain.offense.SpeedingOffense;
import kdg.be.processor.frontend.exception.FineNotFoundException;
import kdg.be.processor.frontend.exception.UnPersistableException;
import kdg.be.processor.repository.FineRepository;
import kdg.be.processor.domain.fine.Fine;
import kdg.be.processor.domain.offense.EmissionOffense;
import kdg.be.processor.domain.offense.Offense;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class FineService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FineService.class);

    @Value("${emissionFineFactor:1.0}")
    private double emissionFineFactor;

    @Value("${speedingFineFactor:1.0}")
    private double speedingFineFactor;

    private FineRepository fineRepository;
    private DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Autowired
    public FineService(FineRepository fineRepository) {
        this.fineRepository = fineRepository;
    }

    //CREATE
    public Fine save(Fine fine) throws UnPersistableException {
        try {
            return fineRepository.save(fine);
        } catch (DataAccessException dae) {
            throw new UnPersistableException("Unable to save");
        }
    }

    //READ
    public Fine load(Long id) throws FineNotFoundException {
        Optional<Fine> optFine = fineRepository.findById(id);
        if (optFine.isPresent()) {
            return optFine.get();
        }
        throw new FineNotFoundException("Fine was not found");
    }

    public List<Fine> getFinesFilteredByDate(String startDateStr, String endDateStr) {
        return fineRepository.findByOffenseTimestampBetween(LocalDateTime.parse(startDateStr), LocalDateTime.parse(endDateStr));
    }

    //UPDATE
    public Fine change(Fine fine) throws FineNotFoundException, UnPersistableException {
        Fine f = load(fine.getId());
        f.setAmount(fine.getAmount());
        f.setUpdateAmountMotivation(fine.getUpdateAmountMotivation());
        return save(f);
    }

    public Fine accept(Long id) throws FineNotFoundException, UnPersistableException {
        Fine f = load(id);
        f.setApproved(true);
        return save(f);
    }

    public double getEmissionFineFactor() {
        return emissionFineFactor;
    }

    public double getSpeedingFineFactor() {
        return speedingFineFactor;
    }

    public List<Fine> loadAll() {
        return fineRepository.findAll();
    }


    public void add(Offense o) {
        if (o instanceof EmissionOffense)
            LOGGER.info(fineRepository.save(new Fine((EmissionOffense) o,
                    emissionFineFactor, LocalDateTime.now())).toString());
        else {
            LOGGER.info(fineRepository.save(new Fine((SpeedingOffense) o,
                    (((SpeedingOffense) o).getCarSpeed() - ((SpeedingOffense) o).getSpeedLimit())
                            * speedingFineFactor, LocalDateTime.now())).toString());
        }
    }


}
