package kdg.be.processor.frontend.controller.rest;

import kdg.be.processor.businesslogic.service.FineService;
import kdg.be.processor.domain.fine.Fine;
import kdg.be.processor.frontend.dto.FineDTO;
import kdg.be.processor.frontend.exception.FineNotFoundException;
import kdg.be.processor.frontend.exception.UnPersistableException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fine")
public class FineRestController {
    @Autowired
    private FineService fineService;
    private final ModelMapper modelMapper;

    public FineRestController(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @GetMapping("/{id}")
    public FineDTO getFine(@PathVariable Long id) throws FineNotFoundException {
        return modelMapper.map(fineService.load(id), FineDTO.class);
    }

    @GetMapping("/all")
    public FineDTO[] getFine() {//same name no problem.    //@PathVariable(id) Long jos
        return modelMapper.map(fineService.loadAll(), FineDTO[].class);
    }

    @GetMapping("/allow/{id}")
    public ResponseEntity<FineDTO> allowFine(@PathVariable Long id) throws FineNotFoundException, UnPersistableException {
        return new ResponseEntity<>(modelMapper.map(fineService.accept(id), FineDTO.class), HttpStatus.CREATED);
    }


    @PostMapping("/changefine")
    public ResponseEntity<FineDTO> changeFine(@RequestBody FineDTO fineDTO) throws FineNotFoundException, UnPersistableException {
        return new ResponseEntity<>(
                modelMapper.map(fineService.change(modelMapper.map(fineDTO, Fine.class)), FineDTO.class),
                HttpStatus.CREATED);
    }

    //http://localhost:9090/api/fine/fromtill?from=2018-10-16T19:01:18.555141&till=2018-10-31T19:05:18.555141
    @GetMapping("/fromtill")
    public FineDTO[] getFine(@RequestParam String startDate, @RequestParam String endDate) {
        List<Fine> x =fineService.getFinesFilteredByDate(startDate,endDate);
        return modelMapper.map(x, FineDTO[].class);
    }
}