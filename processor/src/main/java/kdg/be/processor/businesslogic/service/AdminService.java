package kdg.be.processor.businesslogic.service;

import kdg.be.processor.domain.user.Admin;
import kdg.be.processor.frontend.exception.UnPersistableException;
import kdg.be.processor.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AdminService implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public AdminService(AdminRepository ar, PasswordEncoder encoder) {
        this.adminRepository = ar;
        this.encoder = encoder;
    }

    @PostConstruct
    private void post() throws UnPersistableException {
        Admin a = new Admin("q", "q", new String[]{"ADMIN"});
        save(a);
    }

    //CREATE
    public UserDetails save(Admin admin) /*throws UnableToPersistException */ {
        admin.setPassword(encoder.encode(admin.getPassword()));
        return buildUser(adminRepository.save(admin));
    }

    //READ
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUsername(username);
        if (admin.getUsername().equalsIgnoreCase(username)) {
            return buildUser(admin);
        } else {
            throw new UsernameNotFoundException("No valid user");
        }
    }

    public List<UserDetails> loadAll() {
        List<UserDetails> list = new ArrayList<>();
        adminRepository.findAll().forEach(a -> list.add(buildUser(a)));
        return list;
    }

    //UPDATE
    public UserDetails update(Admin admin) throws UsernameNotFoundException {
        Admin a = adminRepository.getOne(admin.getId());
        return buildUser(adminRepository.save(admin));
    }

    //DELETE
    public void delete(Admin admin) {
        adminRepository.delete(admin);
    }


    private UserDetails buildUser(Admin admin) {
        return User.withUsername(admin.getUsername())
                .password(admin.getPassword())
                .roles(admin.getRoles())
                .build();
    }
}
