package kdg.be.processor.frontend.controller;

import kdg.be.processor.businesslogic.service.AdminService;
import kdg.be.processor.domain.user.Admin;
import kdg.be.processor.domain.user.AdminDTO;
import kdg.be.processor.frontend.exception.AdminNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@Component
@RestController
public class UserRestController {

  private AdminService adminService;
  private ModelMapper modelMapper;

  @Autowired
  public UserRestController(AdminService adminService, ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
    this.adminService = adminService;
  }

  @GetMapping("/admin/{id}")
  public ResponseEntity<AdminDTO> FindAdmin(@PathVariable int id) {
    try {
      Admin admin = adminService.load((long) id).orElseThrow(AdminNotFoundException::new);
      return new ResponseEntity<>(modelMapper.map(admin, AdminDTO.class), OK);
    } catch (AdminNotFoundException e) {
      e.getMessage();
    }
    //TODO: hoe moet ik dit oplossen?
    return new ResponseEntity<>(modelMapper.map(new Admin("",""), AdminDTO.class), NO_CONTENT);
  }
}
