package com.gleb.springintrodiction.service;

import com.gleb.springintrodiction.dto.Cars;
import com.gleb.springintrodiction.dto.CarsDto;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarsServiceImpl implements CarsService {
    private static List<Cars> carsDB;
    private static List<Cars> temp = new ArrayList();
    private static CarsDto carsDto = new CarsDto();

    static{
        temp.add(new Cars("Shelby GT500", 1967));
        temp.add(new Cars("Impala SS", 1967));
        temp.add(new Cars("Pontiac GTO", 1969));
        temp.add(new Cars("Porsche 911 ", 1973));
        carsDto.setCarsList(temp);
        carsDB = carsDto.getCarsList();
    }


    public static List<Cars> getCarsDB() {
        return carsDB;
    }

    @Override
    public boolean addCar(Cars cars) {
        return carsDB.add(cars);
    }

    @Override
    public List<Cars> getCarByYear(Integer year) {
        return carsDB.stream().filter(cars -> cars.getYear().equals(year))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateCar(String model, Integer year) {
        if (isContainCar(model)) {
        carsDB.stream().filter(cars -> cars.getModel().equals(model))
                .findFirst().get().setYear(year);
        return true;
        } else {
            return false;
        }
    }

    private boolean isContainCar(String model) {
        return carsDB.stream().anyMatch(cars -> cars.getModel().equals(model));
    }

    @Override
    public boolean removeCar(String model) {
        return carsDB.removeIf(cars -> cars.getModel().equals(model));
    }
}
