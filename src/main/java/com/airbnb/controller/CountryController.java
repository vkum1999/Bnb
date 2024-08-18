package com.airbnb.controller;

import com.airbnb.Repository.CountryRepository;
import com.airbnb.Service.CountryService;
import com.airbnb.payload.CountryDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {
    private CountryService countryService;
    private CountryRepository countryRepository;

    public CountryController(CountryService countryService, CountryRepository countryRepository) {
        this.countryService = countryService;
        this.countryRepository = countryRepository;
    }

    @PostMapping("/addCountry")
    public ResponseEntity<CountryDto> addCountry(@RequestBody CountryDto countryDto) {
        CountryDto savedCountry = countryService.addCountry(countryDto);
        return new ResponseEntity<>(savedCountry, HttpStatus.CREATED);
    }

}
