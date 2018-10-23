package kdg.be.processor.UI.controller;

import kdg.be.processor.BL.manager.AdminManager;
import kdg.be.processor.BL.manager.FineManager;
import kdg.be.processor.DAL.FineRepository;
import kdg.be.processor.Domain.fine.Fine;
import kdg.be.processor.Domain.fine.FineDTO;
import kdg.be.processor.Domain.offense.EmissionOffense;
import kdg.be.processor.Domain.user.Admin;
import kdg.be.processor.Domain.user.AdminDTO;
import kdg.be.processor.ProcessorApplication;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@Component
@RestController
@RequestMapping("/api")
public class ProcessorRestController {
  private static final Logger LOGGER = LoggerFactory.getLogger(ProcessorRestController.class);

  private FineManager fineManager;
  private AdminManager adminManager;
  private ModelMapper modelMapper;

  @Autowired
  public ProcessorRestController(FineManager fineManager, AdminManager adminManager, ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
    this.fineManager = fineManager;
    this.adminManager = adminManager;

  }


  @RequestMapping(value = "approvefine/{id}", method = PUT)
  public ResponseEntity<FineDTO[]> approveFine(@PathVariable int id) throws Exception {
    return null;
  }

  @RequestMapping(value = "fine/all", method = GET)
  public ResponseEntity<FineDTO[]> getAllFines() {
    return new ResponseEntity<>(modelMapper.map(fineManager.loadAll(), FineDTO[].class), OK);
  }
//  @RequestMapping(value = "motivatefine/{id}", method = PUT)
//  @ResponseStatus(OK)
//  public ResponseEntity<> motivateFine(
//          @PathVariable int id,
//          @RequestBody final String motivation,
//          @RequestBody final double amount) throws Exception {
//
//    Fine fine = fineManager.load((long) id).orElseThrow(NullPointerException::new);
//    fine.setUpdateAmountMotivation(motivation);
//    return null;
//  }


  //  lijst van boetes (filterbaar tussen 2 tijdstippen
  @GetMapping(name = "/fines/between")
  public ResponseEntity<FineDTO[]> findAll(
          @RequestBody
          @RequestParam(name = "startDate") String startDate,
          @RequestParam(name = "endDate") String endDate) {
    LOGGER.error(startDate + endDate);
    return new ResponseEntity<>(modelMapper.map(
            fineManager.getFines(startDate, endDate), FineDTO[].class), HttpStatus.OK);
  }


  @GetMapping("/admin/{id}")
  public ResponseEntity<AdminDTO> FindAdmin(@PathVariable int id) throws Exception {
    Admin admin = adminManager.load((long) id).get();
    return new ResponseEntity<>(modelMapper.map(admin, AdminDTO.class), OK);
  }

}
