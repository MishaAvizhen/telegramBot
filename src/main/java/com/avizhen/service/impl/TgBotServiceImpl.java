package com.avizhen.service.impl;

import com.avizhen.entity.City;
import com.avizhen.service.CityService;
import com.avizhen.service.TgBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TgBotServiceImpl implements TgBotService {
    private CityService cityService;

    @Autowired
    public TgBotServiceImpl(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    public String showCityInfo(String cityName) {

        City city;
        try {
            city = cityService.findByName(cityName);

        } catch (Exception e) {
            return e.getMessage();
        }

        return city.getInfo();
    }
}
