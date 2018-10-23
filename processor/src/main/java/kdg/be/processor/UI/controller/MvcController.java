package kdg.be.processor.UI.controller;

import kdg.be.processor.DAL.FineRepository;
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
  private static final Logger LOGGER = LoggerFactory.getLogger(MvcController.class);

  @Autowired
  public MvcController(FineRepository offenseRepo) {
    this.fineRepository = offenseRepo;
  }

  @GetMapping("/offenses")
  public String fine(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {

    model.addAttribute("offenses", fineRepository.findAll());

    return "offenses";
  }

}
