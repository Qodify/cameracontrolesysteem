package kdg.be.processor.BL.manager;

import kdg.be.processor.DAL.FineRepository;
import kdg.be.processor.Domain.fine.Fine;
import kdg.be.processor.Domain.offense.Offense;
import org.springframework.stereotype.Component;

@Component
public class FineManager {
  private final double BEDRAG = 123;

  private FineRepository fineRepository;

  public FineManager(FineRepository fineRepository) {
    this.fineRepository = fineRepository;
  }

  public void add(Offense o) {
    fineRepository.save(new Fine(o, BEDRAG));
  }
}