package kdg.be.processor.businesslogic.service;

import kdg.be.processor.repository.AdminRepository;
import kdg.be.processor.domain.user.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminService {

  @Autowired
  private AdminRepository adminRepository;

  public Optional<Admin> load(Long id) {
    return adminRepository.findById((id));
  }
}
