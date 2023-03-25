package com.srv.vaccinationCentre.service;

import com.srv.vaccinationCentre.entity.VaccinationCentre;

import java.util.Optional;

public interface VaccinationCentreService {

    VaccinationCentre addCentre(VaccinationCentre vaccinationCentre);

    Optional<VaccinationCentre> findById(Integer id);


}
