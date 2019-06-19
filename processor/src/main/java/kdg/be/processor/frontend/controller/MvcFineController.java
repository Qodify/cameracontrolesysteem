package kdg.be.processor.frontend.controller;

import kdg.be.processor.businesslogic.service.FineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Component
@Controller
@RequestMapping("/fine")
public class MvcFineController {
  private static final Logger LOGGER = LoggerFactory.getLogger(MvcFineController.class);

  private final FineService fineService;

  @Autowired
  public MvcFineController(FineService fineService) {
    this.fineService = fineService;
  }

  @RequestMapping(value = {"/finefactors"}, method = RequestMethod.GET)
  public String index(Model model) {

    model.addAttribute("eff", fineService.getEmissionFineFactor());
    model.addAttribute("sff", fineService.getSpeedingFineFactor());
    return "finefactors";
  }

}
