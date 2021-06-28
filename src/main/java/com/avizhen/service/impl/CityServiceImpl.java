package com.avizhen.service.impl;

import com.avizhen.converter.impl.CityConverter;
import com.avizhen.dto.CityDto;
import com.avizhen.entity.City;
import com.avizhen.exception.ResourceAlreadyExistException;
import com.avizhen.exception.ResourceNotFoundException;
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
        City city = cityRepository.findByName(name);
        if (city == null) {
            throw new ResourceNotFoundException("Resource with name " + name + " not found");
        }
        return city;
    }

    @Override
    public City findById(Integer id) {
        Optional<City> cityOptional = cityRepository.findById(id);
        return cityOptional.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public City createCity(CityDto cityDto) {
        if (cityRepository.findByName(cityDto.getName()) != null) {
            throw new ResourceAlreadyExistException("Resource with name " + cityDto.getName() + " already exist");
        }
        City city = cityConverter.convertToEntity(cityDto);
        return cityRepository.save(city);
    }

    @Override
    public City updateCity(CityDto cityDto, Integer id) {
        City cityToUpdate = this.findById(id);
        City city = cityConverter.convertToExistingEntity(cityDto, cityToUpdate);
        String name = city.getName();
        City cityToCompare = this.findByName(name);
        Integer idInDb = cityToCompare.getId();
        if (!idInDb.equals(id)) {
            throw new ResourceAlreadyExistException("Resource with name " + cityDto.getName() + " already exist update");
        }
        return cityRepository.saveAndFlush(city);
    }

    @Override
    public void delete(Integer id) {
        Optional<City> byId = cityRepository.findById(id);
        if (byId.isPresent()) {
            cityRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(id);
        }
    }
}
