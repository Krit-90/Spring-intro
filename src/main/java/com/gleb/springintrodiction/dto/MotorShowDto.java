package com.gleb.springintrodiction.dto;

import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@XmlAccessorType
public class MotorShowDto {
    private String title;
    private String city;
    private List<String> carsDtosModel;

    public MotorShowDto(String title, String city, List<String> carsDtosModel) {
        this.title = title;
        this.city = city;
        this.carsDtosModel = carsDtosModel;
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

    public List<String> getCarsDtosModel() {
        return carsDtosModel;
    }

    public void setCarsDtosModel(List<String> carsDtosModel) {
        this.carsDtosModel = carsDtosModel;
    }
}

