package com.srv.vaccinationCentre.controller;

import com.srv.vaccinationCentre.entity.RequiredResponse;
import com.srv.vaccinationCentre.entity.VaccinationCentre;
import com.srv.vaccinationCentre.service.VaccinationCentreService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/vaccinationCentre")
public class VaccinationCentreController {

    @Autowired
    private VaccinationCentreService vaccinationCentreService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(path = "/add")
    public ResponseEntity<VaccinationCentre> addVaccineCentre(@RequestBody VaccinationCentre newVaccinationCentre){
        VaccinationCentre vaccinationCentre = vaccinationCentreService.addCentre(newVaccinationCentre);
        return new ResponseEntity<>(vaccinationCentre, HttpStatus.OK);
    }

    @GetMapping(path = "/id/{id}")
    @CircuitBreaker(name = "citizenService", fallbackMethod = "handleCitizenDownTime")
    public ResponseEntity<RequiredResponse> getAllDataByCentreId(@PathVariable Integer id){

        RequiredResponse requiredResponse = new RequiredResponse();
        //get vaccination centre details and add that to the requiredResponse
        VaccinationCentre vaccinationCentreName = vaccinationCentreService.findById(id).get();
        requiredResponse.setCentre(vaccinationCentreName);

        //get all the citizens registered to this vaccination centre

        /*
        The below syntax is not a good practice, rather we should use the service name which is
        registered on EUREKA SERVER, but that will not work directly as it will require a load balanced
        rest template - use @loadBalanced annotation on the rest template bean .

        List citizens = restTemplate.getForObject("http://localhost:9090/citizen/id/" + id, List.class);
         */

        List citizens = restTemplate.getForObject("http://CITIZEN-SERVICE/citizen/id/" + id, List.class);
        requiredResponse.setCitizens(citizens);

        return new ResponseEntity<>(requiredResponse, HttpStatus.OK);
    }

    public ResponseEntity<RequiredResponse> handleCitizenDownTime(Integer id, Exception e){
        RequiredResponse requiredResponse = new RequiredResponse();
        VaccinationCentre vaccinationCentreName = vaccinationCentreService.findById(id).get();
        requiredResponse.setCentre(vaccinationCentreName);
        return new ResponseEntity<>(requiredResponse, HttpStatus.OK);
    }
}
