package kdg.be.processor.businesslogic.service;

import kdg.be.processor.repository.FineRepository;
import kdg.be.processor.domain.fine.Fine;
import kdg.be.processor.domain.offense.EmissionOffense;
import kdg.be.processor.domain.offense.Offense;
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

  private static final double BEDRAG = 123;
  @Value("${emissionFineFactor:1.0}")
  private double emissionFineFactor;

  @Value("${speedingPlateFactor:1.0}")
  private double speedingPlateFactor;

  private FineRepository fineRepository;
  private DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

  @Autowired
  public FineService(FineRepository fineRepository) {
    this.fineRepository = fineRepository;
  }

  public List<Fine> getFinesFilteredByDate(String ldd1, String ldd2) {
    LocalDateTime startDate = LocalDateTime.parse(ldd1, formatter);
    LocalDateTime endDate = LocalDateTime.parse(ldd2, formatter);
    ArrayList<Fine> fines = new ArrayList<>();
    loadAll()
            .stream()
            .filter(x -> x.getTimestamp().isAfter(startDate) && x.getTimestamp().isBefore(endDate))
            .forEach(fines::add);
    return fines;
  }

  public double getEmissionFineFactor() {
    return emissionFineFactor;
  }

  public double getSpeedingPlateFactor() {
    return speedingPlateFactor;
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
      fineRepository.save(new Fine(o, BEDRAG * emissionFineFactor, LocalDateTime.now()));
    else
      fineRepository.save(new Fine(o, BEDRAG, LocalDateTime.now()));

  }
}
