package com.tse.mobtracker.controller;

import com.tse.mobtracker.entity.Mobility;
import com.tse.mobtracker.repository.MobilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@Controller
@RequestMapping(path = "/mobilities")
public class MobilityController {
    @Autowired
    private MobilityRepository mobilityRepository;


    private Mobility setMobilityFields(
            Mobility mob,
            String studentName,
            Long prom,
            String city,
            String destinationCountry,
            String beginDate,
            String endDate

    ) {
        mob.setStudentName(studentName);
        mob.setProm(prom);
        mob.setCity(city);
        mob.setDestinationCountry(destinationCountry);
        mob.setBeginDate(Date.valueOf(beginDate));
        mob.setEndDate(Date.valueOf(endDate));

        return mob;
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    String addNewMobility(
            @RequestParam String studentName,
            @RequestParam Long prom,
            @RequestParam String city,
            @RequestParam String destinationCountry,
            @RequestParam String beginDate,
            @RequestParam String endDate) {

        Mobility mob = setMobilityFields(new Mobility(), studentName, prom, city, destinationCountry, beginDate, endDate);

        mobilityRepository.save(mob);

        return "Saved";
    }

    @GetMapping(path = "/all")
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

    @PatchMapping(path = "/{id}")
    public void updateMobility(
            @PathVariable(value = "id") final Integer id,
            @RequestParam String studentName,
            @RequestParam Long prom,
            @RequestParam String city,
            @RequestParam String destinationCountry,
            @RequestParam String beginDate,
            @RequestParam String endDate) {

        Mobility mob = setMobilityFields(mobilityRepository.findById(id).orElseThrow(), studentName, prom, city, destinationCountry, beginDate, endDate);
        mobilityRepository.save(mob);

    }


}
