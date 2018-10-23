package kdg.be.processor.DAL;

import kdg.be.processor.Domain.user.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface AdminRepository extends JpaRepository<Admin, Long> {
  @Override
  Optional<Admin> findById(Long aLong);

  @Override
  void deleteById(Long aLong);

  @Override
  <S extends Admin> S save(S entity);
}
