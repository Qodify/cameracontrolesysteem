package kdg.be.processor.BL.manager;

import kdg.be.processor.DAL.FineRepository;
import kdg.be.processor.Domain.fine.Fine;
import kdg.be.processor.Domain.offense.EmissionOffense;
import kdg.be.processor.Domain.offense.Offense;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class FineManager {
  private final double BEDRAG = 123;
  private DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
  private FineRepository fineRepository;


  public FineManager(FineRepository fineRepository) {
    this.fineRepository = fineRepository;
  }

  public void add(Offense o) {

    fineRepository.save(new Fine(o, BEDRAG, LocalDateTime.now()));
  }

  public Optional<Fine> load(Long id) {
    return fineRepository.findById((id));
  }

  public void save(Fine fine) {
    fineRepository.save(fine);
  }

  public Collection<Fine> getFines(String ldd1, String ldd2) {

    var startDate = LocalDateTime.parse(ldd1, formatter);
    var endDate = LocalDateTime.parse(ldd2, formatter);
    ArrayList<Fine> fines = new ArrayList<>();
    fineRepository.findAll()
            .stream()
            .filter(x -> x.getTimestamp().isAfter(startDate) && x.getTimestamp().isBefore(endDate))
            .forEach(fines::add);
    return fines;
  }

  public List<Fine> loadAll() {
    return fineRepository.findAll();
  }


}