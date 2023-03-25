package com.srv.vaccinationCentre.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccinationCentre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String centreName;

    private String address;

}
