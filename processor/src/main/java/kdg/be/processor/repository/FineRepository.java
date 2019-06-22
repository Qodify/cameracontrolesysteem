package kdg.be.processor.repository;

import kdg.be.processor.domain.fine.Fine;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FineRepository extends CrudRepository<Fine, Long> {

  @Override
  List<Fine> findAll();

  @Override
  Optional<Fine> findById(Long aLong);

}
