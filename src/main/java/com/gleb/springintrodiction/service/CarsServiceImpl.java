package com.gleb.springintrodiction.service;

import com.gleb.springintrodiction.dto.CarDto;
import com.gleb.springintrodiction.dto.CarsListDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarsServiceImpl implements CarsService {
    private List<CarDto> carDtoDB;

    public CarsServiceImpl() {
        CarsListDto carsListDto = new CarsListDto();
        carDtoDB = carsListDto.getCarDB();
    }

    public List<CarDto> getCarsDtoDB() {
        return carDtoDB;
    }

    @Override
    public boolean addCar(CarDto carDto) {
        return carDtoDB.add(carDto);
    }

    @Override
    public List<CarDto> getCarsByYear(Integer year) {
        return carDtoDB.stream().filter(carDto -> carDto.getYear().equals(year))
                .collect(Collectors.toList());
    }

    @Override
    public List<CarDto> getCarsByModel(String model) {
        return carDtoDB.stream().filter(carDto -> carDto.getModel().equals(model))
                .collect(Collectors.toList());
    }

    @Override
    public List<CarDto> getCarsByModelAndYear(CarDto carDto) {
        List<CarDto> filteredList = new ArrayList<>();
        if (carDto.getYear() != null & carDto.getModel() != null) {
            return carDtoDB.stream().filter(c -> carDto.getModel().equals(carDto.getModel()) &&
                    carDto.getYear().equals(carDto.getYear()))
                    .collect(Collectors.toList());
        } if (carDto.getModel() == null) {
            return  getCarsByYear(carDto.getYear());
        } else {
            return getCarsByModel(carDto.getModel());
        }
    }

    @Override
    public boolean updateCar(Integer id, CarDto c) {
        if (isContainCar(id)) {
            // TODO: Если возвращается Optional, это значит, что нельзя просто взять и сделать .get()
            //  Разве проверка в предыдущей строчке не поможет? если обекта с таким id нет, то код и не выполнится
            CarDto searchingCarDto = carDtoDB.stream().filter(carDto -> carDto.getId().equals(id)).findFirst().get();
            if (c.getModel() != null) {
                searchingCarDto.setModel(c.getModel());
            }
            if (c.getYear() != null) {
                searchingCarDto.setYear(c.getYear());
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean removeCar(Integer id) {
        return carDtoDB.removeIf(carDto -> carDto.getId().equals(id));
    }

    private boolean isContainCar(Integer id) {
        return carDtoDB.stream().anyMatch(carDto -> carDto.getId().equals(id));
    }
}
