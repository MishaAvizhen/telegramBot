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
        city.setName(dto.getName());
        city.setInfo(dto.getInfo());

        return city;
    }
}
