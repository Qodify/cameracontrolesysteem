package kdg.be.processor.frontend.controller;

import kdg.be.processor.businesslogic.service.AdminService;
import kdg.be.processor.domain.user.Admin;
import kdg.be.processor.domain.user.AdminDTO;
import kdg.be.processor.frontend.exception.AdminNotFoundException;
import kdg.be.processor.frontend.exception.UnPersistableException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private final AdminService adminService;
    private final ModelMapper modelMapper;

    public UserRestController(final ModelMapper modelMapper, AdminService adminService) {
        this.modelMapper = modelMapper;
        this.adminService = adminService;
    }

    @GetMapping("/{username}")
    public UserDetails getUser(@PathVariable String username) throws AdminNotFoundException {//same name no problem.    //@PathVariable(id) Long jos
        return adminService.loadUserByUsername(username);
    }


    @PostMapping("/change")
    public ResponseEntity<UserDetails> changeAppUser(@RequestBody AdminDTO adminUserDTO) throws AdminNotFoundException, UnPersistableException {
        return new ResponseEntity<>(adminService.update(modelMapper.map(adminUserDTO, Admin.class)), HttpStatus.CREATED);

    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDetails>> loadAll() throws AdminNotFoundException, UnPersistableException {
        return new ResponseEntity<>(adminService.loadAll(), HttpStatus.CREATED);
    }


    @PostMapping("/create")
    public ResponseEntity<UserDetails> createAppUser(@RequestBody AdminDTO adminUserDTO) throws UnPersistableException {
        return new ResponseEntity<>(adminService.save(modelMapper.map(adminUserDTO, Admin.class)), HttpStatus.CREATED);

    }

    @PostMapping("/delete")
    public ResponseEntity deleteAppUser(@RequestBody AdminDTO adminUserDTO) {
        adminService.delete(modelMapper.map(adminUserDTO, Admin.class));
        return new ResponseEntity(HttpStatus.OK);
    }
}


