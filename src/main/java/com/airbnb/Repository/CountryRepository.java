package com.airbnb.Repository;

import com.airbnb.Entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository <Country,Long> {

}
