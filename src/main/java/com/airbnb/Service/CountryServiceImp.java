package com.airbnb.Service;

import com.airbnb.Entity.Country;
import com.airbnb.Repository.CountryRepository;
import com.airbnb.payload.CountryDto;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImp  implements  CountryService{

    private CountryRepository countryRepository;

    public CountryServiceImp(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    @Override
    public CountryDto addCountry(CountryDto countryDto) {
        Country entity = MaptoEntity(countryDto);
        Country save = countryRepository.save(entity);
        CountryDto Dto1 = MaptoDto(save);
        Dto1.setMessage("data added succecfully");
        return Dto1;
    }

    Country MaptoEntity(CountryDto countryDto){
        Country  enity=new Country();
        enity.setId(countryDto.getId());
        enity.setName(countryDto.getName());
        return  enity;
    }
    CountryDto MaptoDto(Country country){
        CountryDto dto=new CountryDto();
        dto.setId(country.getId());
        dto.setName(country.getName());
        return  dto;
    }
}
