package com.avizhen.controller;

import com.avizhen.dto.CityDto;
import com.avizhen.entity.City;
import com.avizhen.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeCity(@PathVariable Integer id) {
        cityService.delete(id);
        return new ResponseEntity<>(" City was deleted ", HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateCity(@PathVariable Integer id,
                                             @RequestBody CityDto cityDto) {
        cityService.updateCity(cityDto, id);
        return new ResponseEntity<>("City was updated", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addCity(@RequestBody CityDto cityDto) {
        cityService.createCity(cityDto);
        return new ResponseEntity<>("City was  added", HttpStatus.OK);
    }
}
