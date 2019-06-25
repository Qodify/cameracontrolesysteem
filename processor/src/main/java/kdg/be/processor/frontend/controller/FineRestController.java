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

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@Component
@RestController
@RequestMapping("/api/fine")
public class FineRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FineRestController.class);

    private final FineService fineService;
    private AdminService adminService;
    private ModelMapper modelMapper;

    @Autowired
    public FineRestController(AdminService adminService, ModelMapper modelMapper, FineService fineService) {
        this.modelMapper = modelMapper;
        this.adminService = adminService;
        this.fineService = fineService;
    }


    @RequestMapping(value = "approvefine/{id}", method = PUT)
    public ResponseEntity<FineDTO[]> approveFine(@PathVariable int id) {
        return null;
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
