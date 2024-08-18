package com.airbnb.payload;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CountryDto {
    @Id

    private Long id;

    private String name;
    private String message;

}