package kdg.be.processor.businesslogic.service;

import kdg.be.processor.domain.offense.SpeedingOffense;
import kdg.be.processor.repository.FineRepository;
import kdg.be.processor.domain.fine.Fine;
import kdg.be.processor.domain.offense.EmissionOffense;
import kdg.be.processor.domain.offense.Offense;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    public List<Fine> getFinesFilteredByDate(String startDateStr, String endDateStr) {
        LocalDateTime startDate = LocalDateTime.parse(startDateStr, formatter);
        LocalDateTime endDate = LocalDateTime.parse(endDateStr, formatter);
        List<Fine> fines = new ArrayList<>();
        loadAll()
                .stream()
                .filter(x -> x.getTimestamp().isAfter(startDate) && x.getTimestamp().isBefore(endDate))
                .forEach(fines::add);
        return fines;
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

    public Optional<Fine> load(Long id) {
        return fineRepository.findById((id));
    }

    public void save(Fine fine) {
        fineRepository.save(fine);
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
