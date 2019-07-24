package kdg.be.processor.repository;

import kdg.be.processor.domain.fine.Fine;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FineRepository extends CrudRepository<Fine, Long> {
  List<Fine> findAll();
  Optional<Fine> findById(Long aLong);
    List<Fine> findByOffenseTimestampBetween(LocalDateTime startDate,LocalDateTime endDate);


}
