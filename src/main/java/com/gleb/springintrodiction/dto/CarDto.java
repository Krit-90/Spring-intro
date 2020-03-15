package com.gleb.springintrodiction.dto;

import javax.xml.bind.annotation.XmlType;

@XmlType
public class CarDto {


    private String model;
    private Integer year;
    private MotorShowDto motorShow;

    public CarDto(String model, Integer year, MotorShowDto motorShow) {
        this.model = model;
        this.year = year;
        this.motorShow = motorShow;
    }

    public CarDto(String model, Integer year) {
        this.model = model;
        this.year = year;
    }

    public CarDto() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public MotorShowDto getMotorShow() {
        return motorShow;
    }

    public void setMotorShow(MotorShowDto motorShow) {
        this.motorShow = motorShow;
    }
}
