package com.airbnb.Service;

import com.airbnb.payload.CountryDto;
import org.springframework.stereotype.Service;

@Service
public interface CountryService {


    CountryDto addCountry(CountryDto countryDto);
}
