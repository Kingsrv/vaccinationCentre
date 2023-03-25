package com.srv.vaccinationCentre.repository;

import com.srv.vaccinationCentre.entity.VaccinationCentre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinationCentreRepository extends JpaRepository<VaccinationCentre, Integer> {
}
