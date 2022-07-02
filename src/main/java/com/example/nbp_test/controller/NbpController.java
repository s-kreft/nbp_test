package com.example.nbp_test.controller;

import com.example.nbp_test.model.AverageRate;
import com.example.nbp_test.service.NbpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nbp")
public class NbpController {
    private NbpService nbpService;

    public NbpController(NbpService nbpService) {
        this.nbpService = nbpService;
    }

    @GetMapping("/data/{currency}/{days}")
    public ResponseEntity<AverageRate> getData(@PathVariable String currency, @RequestParam int days) {
        double value = nbpService.getAverageRateForPeriod(currency, days);
        return ResponseEntity.ok(new AverageRate(currency, days, value));
    }
}
