package com.gleb.springintrodiction.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
// TODO: Во множественном числе сущности не называем - OK
public class CarDto {


    private static Integer counter = 0;
    private final Integer id = counter++;
    private String model;
    private Integer year;

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

    public Integer getId() {
        return id;
    }

}
