package com.avizhen.controller;

import com.avizhen.entity.City;
import com.avizhen.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityRestController {

    private CityService cityService;

    @Autowired
    public CityRestController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/cities")
    public ResponseEntity<List<City>> getAllCities() {
        List<City> allCities = cityService.findAllCities();
        return new ResponseEntity<>(allCities, HttpStatus.OK);
    }

    @GetMapping("/city")
    public ResponseEntity<City> getCity(@RequestParam(value = "name", required = false) String name) {
        City byName = cityService.findByName(name);
        return new ResponseEntity<>(byName, HttpStatus.OK);
    }
}
