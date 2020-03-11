package com.gleb.springintrodiction.dto;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsListDto {
    // TODO: Почему static?
    private static List<CarDto> carDB;

    public CarsListDto() {
        carDB = new ArrayList<>();
        carDB.add(new CarDto("Shelby GT500", 1967));
        carDB.add(new CarDto("Impala SS", 1967));
        carDB.add(new CarDto("Pontiac GTO", 1969));
        carDB.add(new CarDto("Porsche 911 ", 1973));
    }

    @XmlElement
    public List<CarDto> getCarDB() {
        return carDB;
    }

    public void setCarDB(List<CarDto> carDB) {
        this.carDB = carDB;
    }
}
