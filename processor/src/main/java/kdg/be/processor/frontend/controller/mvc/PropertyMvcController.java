package kdg.be.processor.frontend.controller.mvc;


import kdg.be.processor.businesslogic.service.PropertyService;
import kdg.be.processor.frontend.dto.PropertiesDTO;
import kdg.be.processor.frontend.exception.UnPersistableException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/property")
public class PropertyMvcController {
    private final PropertyService ps;


    public PropertyMvcController(PropertyService ps) {
        this.ps = ps;
    }

    @GetMapping("/props")
    public ModelAndView properties() {
        return new ModelAndView("props", "map", new PropertiesDTO(ps.getAll()));
    }

    @RequestMapping(value = "/props.save", method = RequestMethod.POST)
    public String postProperties(@ModelAttribute PropertiesDTO map) throws UnPersistableException {
        ps.save(map.getProp());
        return "redirect:props";
    }
}
