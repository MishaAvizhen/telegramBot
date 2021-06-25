package com.avizhen.service.impl;

import com.avizhen.converter.impl.CityConverter;
import com.avizhen.dto.CityDto;
import com.avizhen.entity.City;
import com.avizhen.repository.CityRepository;
import com.avizhen.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    private CityRepository cityRepository;
    private CityConverter cityConverter;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, CityConverter cityConverter) {
        this.cityRepository = cityRepository;
        this.cityConverter = cityConverter;
    }


    @Override
    public List<City> findAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public City findByName(String name) {
        return cityRepository.findByName(name);
    }

    @Override
    public City findById(Integer id) {
        return cityRepository.getOne(id);
    }

    @Override
    public City createCity(CityDto cityDto) {
        if (cityRepository.findByName(cityDto.getName()) != null) {
            throw new IllegalArgumentException("City already exist");
        }
        City city = cityConverter.convertToEntity(cityDto);
        return cityRepository.save(city);
    }

    @Override
    public City updateCity(CityDto cityDto) {
        City byName = cityRepository.findByName(cityDto.getName());
        if (byName == null) {
            throw new IllegalArgumentException("City not found ");
        }
        City city = cityConverter.convertToEntity(cityDto);
        return cityRepository.saveAndFlush(city);
    }

    @Override
    public void delete(Integer id) {
        Optional<City> byId = cityRepository.findById(id);
        if (byId.isPresent()) {
            cityRepository.deleteById(id);
        }

    }

}
