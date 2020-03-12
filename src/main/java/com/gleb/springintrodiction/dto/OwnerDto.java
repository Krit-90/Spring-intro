package com.gleb.springintrodiction.dto;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class OwnerDto {
    private String firstName;
    private String lastName;
    private List<CarDto> carsDto;

    public OwnerDto(String firstName, String lastName, List<CarDto> carsDto) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.carsDto = carsDto;
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

    public List<CarDto> getCarsDto() {
        return carsDto;
    }

    public void setCarsDto(List<CarDto> carsDto) {
        this.carsDto = carsDto;
    }
}
