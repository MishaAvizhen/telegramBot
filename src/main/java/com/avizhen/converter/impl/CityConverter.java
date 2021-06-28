package com.avizhen.converter.impl;

import com.avizhen.converter.Converter;
import com.avizhen.dto.CityDto;
import com.avizhen.entity.City;
import org.springframework.stereotype.Component;

@Component
public class CityConverter implements Converter<City, CityDto> {
    @Override
    public City convertToEntity(CityDto dto) {
        City city = new City();

        return convertToExistingEntity(dto, city);
    }

    @Override
    public City convertToExistingEntity(CityDto dto, City entity) {
        entity.setName(dto.getName());
        entity.setInfo(dto.getInfo());
        return entity;
    }

}
