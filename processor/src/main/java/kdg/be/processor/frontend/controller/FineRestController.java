package kdg.be.processor.frontend.controller;

import kdg.be.processor.businesslogic.service.AdminService;
import kdg.be.processor.businesslogic.service.FineService;
import kdg.be.processor.domain.fine.Fine;
import kdg.be.processor.domain.fine.FineDTO;
import kdg.be.processor.frontend.exception.FineNotFoundException;
import kdg.be.processor.frontend.exception.UnPersistableException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

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
        return new ResponseEntity<>(modelMapper.map(fineService.change( modelMapper.map(fineDTO, Fine.class)), FineDTO.class), HttpStatus.CREATED);
    }

    //http://localhost:9090/api/fine/fromtill?from=2018-10-16T19:01:18.555141&till=2018-10-31T19:05:18.555141
    @GetMapping("/fromtill")
    public FineDTO[] getFine(@RequestParam String startDate, @RequestParam String endDate) {
        List<Fine> x =fineService.getFinesFilteredByDate(startDate,endDate);
        return modelMapper.map(x, FineDTO[].class);
    }
}