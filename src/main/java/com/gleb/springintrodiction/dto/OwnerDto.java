package com.gleb.springintrodiction.dto;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class OwnerDto {
    private String firstName;
    private String lastName;
    private List<String> carsDtosModel;

    public OwnerDto(String firstName, String lastName, List<String> carsDtosModel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.carsDtosModel = carsDtosModel;
    }

    public OwnerDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getCarsDtosModel() {
        return carsDtosModel;
    }

    public void setCarsDtosModel(List<String> carsDtosModel) {
        this.carsDtosModel = carsDtosModel;
    }
}
