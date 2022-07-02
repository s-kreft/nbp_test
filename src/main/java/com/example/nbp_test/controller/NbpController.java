package com.example.nbp_test.controller;

import com.example.nbp_test.model.AverageRate;
import com.example.nbp_test.service.NbpService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nbp")
public class NbpController {
    private NbpService nbpService;

    public NbpController(NbpService nbpService) {
        this.nbpService = nbpService;
    }

    @ApiOperation("Returns calculated currency rates for specified period")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Succesfull response from NBP API"), @ApiResponse(code = 404, message = "Invalid parameters on some other error occured on the NBP's side")})
    @GetMapping("/data/{currency}")
    public ResponseEntity<AverageRate> getData(@ApiParam(name = "Currency code") @PathVariable String currency, @ApiParam(name = "Period in days") @RequestParam int days) {
        double value = nbpService.getAverageRateForPeriod(currency, days);
        return ResponseEntity.ok(new AverageRate(currency, days, value));
    }
}
