package com.gleb.springintrodiction.dto;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
public class CarsDto{
    private List<Cars> carsList;

    public CarsDto(){

    }
    @XmlElementWrapper
    @XmlElement
    public List<Cars> getCarsList(){
        return carsList;
    }
    public void setCarsList(List<Cars> carsList){
        this.carsList = carsList;
    }
}
