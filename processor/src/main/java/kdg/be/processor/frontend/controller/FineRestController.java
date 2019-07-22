package kdg.be.processor.frontend.controller;

import kdg.be.processor.businesslogic.service.AdminService;
import kdg.be.processor.businesslogic.service.FineService;
import kdg.be.processor.domain.fine.Fine;
import kdg.be.processor.domain.fine.FineDTO;
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

@Component
@RestController
@RequestMapping("/api/fine")
public class FineRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FineRestController.class);

    private final FineService fineService;

    @Autowired
    public FineRestController(FineService fineService) {
        this.fineService = fineService;
    }

    @PutMapping("/change/{id}")
    public ResponseEntity<Fine> updateFine(@PathVariable Long id, @RequestParam("amount") double amount, @RequestParam("motivation") String motivation) {
        if ((motivation == null) || (motivation.length() < 1))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        Optional<Fine> fine = fineService.load(id);
        if(fine.isPresent()){
            fine.get().setAmount(amount);
            fine.get().setUpdateAmountMotivation(motivation);
            fineService.save(fine.get());
            return new ResponseEntity<>(fine.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

    @RequestMapping(value = "approvefine/{id}", method = PUT)
    public ResponseEntity<Fine> approveFine(@PathVariable long id) {
        Optional<Fine> fine = fineService.load(id);
        if(fine.isPresent()){
            fine.get().setApproved(true);
            fineService.save(fine.get());
            return new ResponseEntity<>(fine.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Fine>(HttpStatus.NOT_FOUND);
    }


    @RequestMapping(value = "all", method = GET)
    public ResponseEntity<List<Fine>> getAllFines() {
        return new ResponseEntity<>(fineService.loadAll(), OK);
    }

    @RequestMapping(value = "/fines/between", method = GET)
    public ResponseEntity<List<Fine>> findAll(
            @RequestBody
            @RequestParam(name = "startDate", required = false) String startDate,
            @RequestParam(name = "endDate", required = false) String endDate) {

        return new ResponseEntity<>(
                fineService.getFinesFilteredByDate(startDate, endDate), HttpStatus.OK);
    }


}
