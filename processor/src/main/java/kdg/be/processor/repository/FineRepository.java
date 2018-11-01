package kdg.be.processor.repository;

import kdg.be.processor.domain.fine.Fine;
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
