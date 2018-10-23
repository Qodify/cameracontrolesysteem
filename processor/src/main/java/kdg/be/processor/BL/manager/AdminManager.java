package kdg.be.processor.BL.manager;

import kdg.be.processor.DAL.AdminRepository;
import kdg.be.processor.Domain.user.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminManager {

  @Autowired
  private AdminRepository adminRepository;

  public Optional<Admin> load(Long id) {
    return adminRepository.findById((id));
  }
}
