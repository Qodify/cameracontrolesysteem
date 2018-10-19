package kdg.be.processor.UI.controller;

import kdg.be.processor.DAL.FineRepository;
import kdg.be.processor.Domain.fine.Fine;
import kdg.be.processor.ProcessorApplication;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@org.springframework.web.bind.annotation.RestController
public class RestController {

  private final FineRepository offenseRepo;
  private static final Logger LOGGER = LoggerFactory.getLogger(ProcessorApplication.class);
  private ModelMapper modelMapper;


  @Autowired
  public RestController(FineRepository offenseRepo, ModelMapper modelMapper) {
    this.offenseRepo = offenseRepo;
  }


  @GetMapping("/fines")
  public String fine(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {

    model.addAttribute("fines", offenseRepo.findAll());

    return "offenses";
  }

  @GetMapping("/fines/{id}")
  public ResponseEntity<Fine> loadGreeting(@PathVariable Long id) throws Exception {
//    Fine f = .load(id);
//    return new ResponseEntity<>(modelMapper.map(greeting, GreetingDTO.class), HttpStatus.OK);
  }
}
