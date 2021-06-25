package com.avizhen.service;

import com.avizhen.dto.CityDto;
import com.avizhen.entity.City;

import java.util.List;

public interface CityService {
    List<City> findAllCities();

    City findByName(String name);
    City findById(Integer id);


    City createCity(CityDto cityDto);
    City updateCity(CityDto cityDto);

    void delete(Integer id);


}
