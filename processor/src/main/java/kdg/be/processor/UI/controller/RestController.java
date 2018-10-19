package kdg.be.processor.UI.controller;

import kdg.be.processor.DAL.OffenseRepository;
import kdg.be.processor.Domain.offense.EmissionOffense;
import kdg.be.processor.Domain.offense.SpeedingOffense;
import kdg.be.processor.ProcessorApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@org.springframework.web.bind.annotation.RestController
public class RestController {

  private final OffenseRepository offenseRepo;
  private static final Logger LOGGER = LoggerFactory.getLogger(ProcessorApplication.class);

  @Autowired
  public RestController(OffenseRepository offenseRepo) {
    this.offenseRepo = offenseRepo;
  }

  @RequestMapping("/offense")
  @GetMapping("/offenses")
  public String fine(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {

    //testgegevens
    offenseRepo.save(new EmissionOffense("1-AAA-111", 3, 4, 345));
    offenseRepo.save(new EmissionOffense("1-AAB-111", 2, 4, 345));
    offenseRepo.save(new EmissionOffense("1-AAC-111", 1, 4, 345));
    offenseRepo.save(new SpeedingOffense("1-BBB-111", 50, 70, 65));
    offenseRepo.save(new SpeedingOffense("1-BBA-111", 50, 70, 65));
    offenseRepo.save(new SpeedingOffense("1-BBC-111", 50, 70, 65));

    model.addAttribute("offenses", offenseRepo.findAll());

    return "offenses";
  }
}
