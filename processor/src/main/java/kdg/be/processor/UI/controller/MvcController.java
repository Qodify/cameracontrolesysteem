package kdg.be.processor.UI.controller;

import kdg.be.processor.DAL.FineRepository;
import kdg.be.processor.Domain.fine.Fine;
import kdg.be.processor.Domain.offense.EmissionOffense;
import kdg.be.processor.Domain.offense.SpeedingOffense;
import kdg.be.processor.ProcessorApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@Controller
public class MvcController {

  private final FineRepository fineRepository;
  private static final Logger LOGGER = LoggerFactory.getLogger(ProcessorApplication.class);

  @Autowired
  public MvcController(FineRepository offenseRepo) {
    this.fineRepository = offenseRepo;
  }

  @GetMapping("/offenses")
  public String fine(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {

    //testgegevens
    fineRepository.save(new Fine(345, new EmissionOffense("1-ABC-123", 3, 4)));

    model.addAttribute("offenses", fineRepository.findAll());

    return "offenses";
  }

//  // Login form
//  @RequestMapping("/login.html")
//  public String login() {
//    return "login.html";login
//  }
//
//  // Login form with error
//  @RequestMapping("/login-error.html")
//  public String loginError(Model model) {
//    model.addAttribute("loginError", true);
//    return "login.html";
//  }
}
