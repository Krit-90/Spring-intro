package com.gleb.springintrodiction.dto;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
// TODO: Предыдущий класс - это CarDto, этот скорее CarsListDto
public class CarsDto{
    private List<Cars> carsList;

    public CarsDto(){

    }
    // TODO: Между контроллерами/методами ставим пробелы
    @XmlElementWrapper
    @XmlElement
    public List<Cars> getCarsList(){
        return carsList;
    }
    public void setCarsList(List<Cars> carsList){
        this.carsList = carsList;
    }
}
