package org.virtu.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.virtu.dto.CreateDocDTO;
import org.virtu.dto.InsuranceCalcDTO;
import org.virtu.dto.InsuranceCalcResultDTO;
import org.virtu.service.InsuranceService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Контроллер расчета страховой премии
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/insurance")
public class InsuranceController {

    @Autowired
    private InsuranceService insuranceService;

    @PostMapping(value = "/calculate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InsuranceCalcResultDTO> calc(@RequestBody InsuranceCalcDTO dto) {
        InsuranceCalcResultDTO calculation = insuranceService.calculation(dto);
        return ResponseEntity.ok(calculation);
    }

}
