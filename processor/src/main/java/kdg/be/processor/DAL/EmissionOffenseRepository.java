package kdg.be.processor.DAL;

import kdg.be.processor.Domain.offense.EmissionOffense;
import kdg.be.processor.Domain.offense.Offense;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EmissionOffenseRepository extends CrudRepository<EmissionOffense,Long> {

  Optional<EmissionOffense> findById(Long aLong);
  Optional<EmissionOffense> findByLicenseplate(String licensePlate);
}
