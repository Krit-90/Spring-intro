package com.gleb.springintrodiction.service;

import com.gleb.springintrodiction.dto.CarsDto;
import org.springframework.stereotype.Service;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Service
public class CarsServiceImpl implements CarsService {

    @XmlElement
    private static List<CarsDto> carsDB = new ArrayList<>();
    static {
        carsDB.add(new CarsDto("Shelby GT500", 1967));
        carsDB.add(new CarsDto("Impala SS", 1967));
        carsDB.add(new CarsDto("Pontiac GTO", 1969));
        carsDB.add(new CarsDto("Porsche 911 ", 1973));
    }

    public static List<CarsDto> getCarsDB() {
        return carsDB;
    }

    @Override
    public boolean addCar(CarsDto carsDto) {
        return carsDB.add(carsDto);
    }

    @Override
    public List<CarsDto> getCarByYear(Integer year) {
        return carsDB.stream().filter(carsDto -> carsDto.getYear().equals(year))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateCar(String model, Integer year) {
        if (isContainCar(model)) {
        carsDB.stream().filter(carsDto -> carsDto.getModel().equals(model))
                .findFirst().get().setYear(year);
        return true;
        } else {
            return false;
        }
    }

    private boolean isContainCar(String model) {
        return carsDB.stream().anyMatch(carsDto -> carsDto.getModel().equals(model));
    }

    @Override
    public boolean removeCar(String model) {
        return carsDB.removeIf(carsDto -> carsDto.getModel().equals(model));
    }
}
