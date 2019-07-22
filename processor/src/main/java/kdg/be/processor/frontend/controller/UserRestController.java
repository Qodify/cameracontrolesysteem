package kdg.be.processor.frontend.controller;

import kdg.be.processor.businesslogic.service.AdminService;
import kdg.be.processor.domain.user.Admin;
import kdg.be.processor.domain.user.AdminDTO;
import kdg.be.processor.frontend.exception.AdminNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;

@Component
@RestController
@RequestMapping("/api/admin")
public class UserRestController {

  private AdminService adminService;

  @Autowired
  public UserRestController(AdminService adminService) {
    this.adminService = adminService;
  }
  //CREATE
  @RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
    adminService.add(admin);
    return new ResponseEntity<>(admin, CREATED);
  }
  //READ
  @GetMapping("/{id}")
  public ResponseEntity<Admin> FindAdmin(@PathVariable int id) {
    try {
      Admin admin = adminService.load((long) id).orElseThrow(AdminNotFoundException::new);
      return new ResponseEntity<>(admin, OK);
    } catch (AdminNotFoundException e) {
      e.getMessage();
    }
    return new ResponseEntity<>(new Admin("",""), NO_CONTENT);
  }

  //UPDATE
  @PutMapping("/{id}")
  public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin) {
    adminService.save(admin);
    return new ResponseEntity<>(admin, OK);
  }

  //DELETE
  @DeleteMapping("/{id}")
  public ResponseEntity<Admin> deleteAdmin(@PathVariable long id) {
    adminService.remove(id);
    return new ResponseEntity<>(OK);
  }

}
