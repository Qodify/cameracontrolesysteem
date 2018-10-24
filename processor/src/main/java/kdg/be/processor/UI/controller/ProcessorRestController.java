package kdg.be.processor.UI.controller;

import kdg.be.processor.BL.manager.AdminManager;
import kdg.be.processor.BL.service.FineService;
import kdg.be.processor.Domain.fine.FineDTO;
import kdg.be.processor.Domain.user.Admin;
import kdg.be.processor.Domain.user.AdminDTO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@Component
@RestController
@RequestMapping("/api")
public class ProcessorRestController {
  private static final Logger LOGGER = LoggerFactory.getLogger(ProcessorRestController.class);

  private final FineService fineService;
  private AdminManager adminManager;
  private ModelMapper modelMapper;

  @Autowired
  public ProcessorRestController(AdminManager adminManager, ModelMapper modelMapper, FineService fineService) {
    this.modelMapper = modelMapper;
    this.adminManager = adminManager;
    this.fineService = fineService;
  }


  @RequestMapping(value = "approvefine/{id}", method = PUT)
  public ResponseEntity<FineDTO[]> approveFine(@PathVariable int id)  {
    return null;
  }

  @RequestMapping(value = "fine/all", method = GET)
  public ResponseEntity<FineDTO[]> getAllFines() {
    return new ResponseEntity<>(modelMapper.map(fineService.loadAll(), FineDTO[].class), OK);
  }

  @GetMapping(name = "/fines/between")
  public ResponseEntity<FineDTO[]> findAll(
          @RequestBody
          @RequestParam(name = "startDate") String startDate,
          @RequestParam(name = "endDate") String endDate) {
    return new ResponseEntity<>(modelMapper.map(
            fineService.getFinesFilteredByDate(startDate, endDate), FineDTO[].class), HttpStatus.OK);
  }

  @GetMapping("/admin/{id}")
  public ResponseEntity<AdminDTO> FindAdmin(@PathVariable int id)  {
    Admin admin = adminManager.load((long) id).get();
    return new ResponseEntity<>(modelMapper.map(admin, AdminDTO.class), OK);
  }

}
