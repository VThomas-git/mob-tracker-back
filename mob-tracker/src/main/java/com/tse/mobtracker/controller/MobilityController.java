package com.tse.mobtracker.controller;

import com.tse.mobtracker.entity.Mobility;
import com.tse.mobtracker.repository.MobilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;

@CrossOrigin
@RestController
@RequestMapping(path = "/mobilities")
public class MobilityController {

    @Autowired
    private MobilityRepository mobilityRepository;

    @PostMapping(path = "/")
    public @ResponseBody
    String addNewMobility(
            @RequestBody Mobility mobility) {

        mobility.setSubmitDate(Date.valueOf(LocalDate.now()));

        mobilityRepository.save(mobility);

        return "Saved";
    }

    @GetMapping(path = "/")
    public @ResponseBody
    Iterable<Mobility> getAllMobility() {
        return mobilityRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    Mobility getMobility(
            @PathVariable(value = "id") final Integer id
    ) {
        return mobilityRepository.findById(id).orElseThrow();
    }

    @PutMapping(path = "/{id}")
    public @ResponseBody
    String updateMobility(
            @RequestBody Mobility mobility,
            @PathVariable Integer id
    ) {
        mobilityRepository.findById(id)
                .map(m -> {
                    m.setStudentName(mobility.getStudentName());
                    m.setProm(mobility.getProm());
                    m.setDestinationCountry(mobility.getDestinationCountry());
                    m.setCity(mobility.getCity());
                    m.setBeginDate(mobility.getBeginDate());
                    m.setEndDate(mobility.getEndDate());
                    m.setSubmitDate(Date.valueOf(LocalDate.now()));
                    return mobilityRepository.save(m);
                })
                .orElseGet(() -> {
                    mobility.setSubmitDate(Date.valueOf(LocalDate.now()));
                    return mobilityRepository.save(mobility);
                });
        return ResponseEntity.ok().toString();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteMobility(
            @PathVariable(value = "id") final Integer id
    ) {
        try {
            mobilityRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
