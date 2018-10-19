package kdg.be.processor.DAL;

import kdg.be.processor.Domain.fine.Fine;
import kdg.be.processor.Domain.offense.Offense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface FineRepository extends JpaRepository<Fine, Long> {

  @Override
  List<Fine> findAll();

  @Override
  Optional<Fine> findById(Long aLong);
}
