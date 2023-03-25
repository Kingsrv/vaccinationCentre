package com.srv.vaccinationCentre.service;

import com.srv.vaccinationCentre.entity.VaccinationCentre;
import com.srv.vaccinationCentre.repository.VaccinationCentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VaccineCentreServiceImpl implements VaccinationCentreService{

    @Autowired
    private VaccinationCentreRepository vaccinationCentreRepository;

    @Override
    public VaccinationCentre addCentre(VaccinationCentre vaccinationCentre) {
        return vaccinationCentreRepository.save(vaccinationCentre);
    }

    @Override
    public Optional<VaccinationCentre> findById(Integer id) {
        return vaccinationCentreRepository.findById(id);
    }
}
