package com.gleb.springintrodiction.dto;

import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@XmlAccessorType
public class MotorShowDto {
    private String title;
    private String city;
    private List<CarDto> carsDtos;

    public MotorShowDto(String title, String city, List<CarDto> carsDtos) {
        this.title = title;
        this.city = city;
        this.carsDtos = carsDtos;
    }

    public MotorShowDto(String title, String city) {
        this.title = title;
        this.city = city;
    }

    public MotorShowDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<CarDto> getCarsDtos() {
        return carsDtos;
    }

    public void setCarsDtos(List<CarDto> carsDtos) {
        this.carsDtos = carsDtos;
    }
}

