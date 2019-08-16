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
@RequestMapping("/api/fines")
public class FineRestController {
    private final FineService fineService;
    private final ModelMapper modelMapper;

    public FineRestController(final ModelMapper modelMapper, FineService fineService) {
        this.modelMapper = modelMapper;
        this.fineService = fineService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<FineDTO> getFine(@PathVariable Long id) throws FineNotFoundException {
        return new ResponseEntity<>(modelMapper.map(fineService.load(id), FineDTO.class), HttpStatus.OK);
    }

    @GetMapping()
    public FineDTO[] getFine() {//same name no problem.    //@PathVariable(id) Long jos
        return modelMapper.map(fineService.loadAll(), FineDTO[].class);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fine> changeFine(@PathVariable Long id) throws FineNotFoundException, UnPersistableException {
        return new ResponseEntity<>(fineService.load(id), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/allow")
    public ResponseEntity<Fine> allowFine(@PathVariable Long id) throws FineNotFoundException, UnPersistableException {
        return new ResponseEntity<>(fineService.accept(id), HttpStatus.CREATED);
    }

    //http://localhost:9090/api/fine/fromtill?from=2018-10-16T19:01:18.555141&till=2018-10-31T19:05:18.555141
    @GetMapping("/fromtill")
    public FineDTO[] getFine(@RequestParam String startDate, @RequestParam String endDate) {
        List<Fine> x = fineService.getFinesFilteredByDate(startDate, endDate);
        return modelMapper.map(x, FineDTO[].class);
    }
}