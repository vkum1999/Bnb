package com.airbnb.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "numberof_guests", nullable = false)
    private Integer numberofGuests;

    @Column(name = "numberof_beds", nullable = false)
    private Integer numberofBeds;

    @Column(name = "numberof_bathrooms", nullable = false)
    private Integer numberofBathrooms;

    @Column(name = "numberof_bedrooms", nullable = false)
    private Integer numberofBedrooms;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private city city;

}