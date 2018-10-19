package kdg.be.processor.DAL;

import kdg.be.processor.Domain.offense.Offense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OffenseRepository extends JpaRepository<Offense,Long> {

  Offense findByLicenseplate(String licensePlate);
  @Override
  List<Offense> findAll();

}
