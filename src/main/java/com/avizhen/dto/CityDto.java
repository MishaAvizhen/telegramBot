package com.avizhen.dto;

public class CityDto {
    private String name;
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CityDto() {

    }

    public CityDto(String name, String info) {
        this.name = name;
        this.info = info;
    }
}
