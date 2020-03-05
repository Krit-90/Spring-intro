package com.gleb.springintrodiction.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

// TODO: Здесь можно явно добавить еще всяких полей
@XmlAccessorType(XmlAccessType.FIELD)
// TODO: Во множественном числе сущности не называем
public class Cars {
    @XmlElement
    private String model;
    @XmlElement
    private Integer year;

    public Cars(String model, Integer year) {
        this.model = model;
        this.year = year;
    }

    public Cars() {
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
}
