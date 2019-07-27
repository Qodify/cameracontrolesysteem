package kdg.be.processor.frontend.controller.mvc;

import kdg.be.processor.businesslogic.service.FineService;
import kdg.be.processor.domain.fine.Fine;
import kdg.be.processor.frontend.dto.FineDTO;
import kdg.be.processor.frontend.exception.FineNotFoundException;
import kdg.be.processor.frontend.exception.UnPersistableException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/fine")
public class FineMvcController {

    private final FineService fineService;
    private final ModelMapper modelMapper;

    public FineMvcController(FineService fineService, ModelMapper modelMapper) {
        this.fineService = fineService;
        this.modelMapper = modelMapper;
    }
    @RequestMapping("/login.html")
    public String login() {
        return "login.html";
    }


    @GetMapping("/fines")
    public ModelAndView Fines(){
        return new ModelAndView("fines","boetes",modelMapper.map(fineService.loadAll().toArray(new Fine[0]), FineDTO[].class));
    }

    @GetMapping("/allow/{id}")
    public ModelAndView allowFine(@PathVariable Long id) throws FineNotFoundException, UnPersistableException {
        fineService.accept(id);
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/changefine", method = RequestMethod.POST)
    public ModelAndView changeFine(FineDTO fineDTO) throws FineNotFoundException, UnPersistableException {
        fineService.change( modelMapper.map(fineDTO, Fine.class));
        return new ModelAndView("index");
    }
}
