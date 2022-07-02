package com.example.nbp_test.service;

import com.example.nbp_test.Repository.nbpRepository;
import com.example.nbp_test.model.LogRecord;
import com.example.nbp_test.model.Root;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class NbpService {

    private final nbpRepository repository;
    private final RestTemplate rest;

    public NbpService(RestTemplate rest, nbpRepository repository) {
        this.rest = rest;
        this.repository = repository;
    }

    public double getAverageRateForPeriod(String currency, int days) {
        try {
            var result = rest.getForEntity("http://api.nbp.pl/api/exchangerates/rates/A/" + currency + "/last/" + days + "/?format=json", Root.class);

            var currency_root = result.getBody();
            var averageRate = calculateAverage(currency_root);
            logToDatabase(currency, days, averageRate );
            return averageRate;
        }
        catch (Exception ex)
        {
            throw ex;
        }


    }

    private double calculateAverage(Root currency_root) {
        var count = 0;
        double sum = 0;
        for (var rate : currency_root.rates) {
            sum += rate.mid;
            count++;
        }
        return sum / count;
    }

    private void logToDatabase(String currency, int days, double averageRate) {
        var record = new LogRecord(currency, days, averageRate, LocalDateTime.now());
        repository.save(record);
    }

}
