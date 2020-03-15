package com.gleb.springintrodiction.service;

import com.gleb.springintrodiction.data.Car;
import com.gleb.springintrodiction.dto.CarDto;
import com.gleb.springintrodiction.dto.MotorShowDto;
import com.gleb.springintrodiction.repository.CarRepository;
import com.gleb.springintrodiction.repository.MotorShowRepository;
import com.gleb.springintrodiction.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarsServiceImpl implements CarsService {

    // TODO: Модификаторы доступа

    @Autowired
    CarRepository carRepository;
    @Autowired
    MotorShowRepository motorShowRepository;
    @Autowired
    OwnerRepository ownerRepository;


    public List<CarDto> getCarsDtoDB() {
        return mapCarToCarDto(carRepository.findAll());
    }

    @Override
    public void addCar(CarDto carDto) {
        carRepository.save(new Car(carDto.getModel(), carDto.getYear()));
    }

    public boolean addMotorShowToCarById(Long motorShowId, Long id) {
        boolean isExistMotorShow = motorShowRepository.findById(motorShowId).isPresent();
        carRepository.updateMotorShowId(motorShowId, id);
        return isExistMotorShow;
    }

    public boolean addOwnerToCarById(Long ownerId, Long id) {
        boolean isExistOwner = ownerRepository.findById(ownerId).isPresent();
        carRepository.updateOwnerId(ownerId, id);
        return isExistOwner;
    }

    @Override
    public List<CarDto> getCarsByYear(Integer year) {
        return mapCarToCarDto(carRepository.findCarsByYear(year));
    }

    @Override
    public List<CarDto> getCarsByModel(String model) {
        return mapCarToCarDto(carRepository.findCarsByModel(model));
    }

    @Override
    public List<CarDto> getCarsByModelAndYear(CarDto carDto) {
        List<CarDto> filteredList = new ArrayList<>();
        if (carDto.getYear() != null & carDto.getModel() != null) {
            return mapCarToCarDto(carRepository.findCarsByModelAndYear(carDto.getModel(), carDto.getYear()));
        }
        if (carDto.getModel() == null) {
            return mapCarToCarDto(carRepository.findCarsByYear(carDto.getYear()));
        } else {
            return mapCarToCarDto(carRepository.findCarsByModel(carDto.getModel()));
        }
    }

    @Override
    public boolean updateCar(Long id, CarDto c) {
        if (carRepository.existsById(id)) {
            // TODO: Если возвращается Optional, это значит, что нельзя просто взять и сделать .get()
            //  Разве проверка в предыдущей строчке не поможет? если обекта с таким id нет, то код и не выполнится
            Car searchingCarDto = carRepository.findById(id).get();
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
    public boolean removeCar(Long id) {
        if(carRepository.existsById(id)){
            carRepository.deleteById(id);
        }
        return false;
    }

    @Override
    public List<CarDto> mapCarToCarDto(List<Car> carList) {
        return carList.stream().map(car -> new CarDto(car.getModel(), car.getYear(),
                new MotorShowDto(car.getMotorShow().getTitle(), car.getMotorShow().getCity())))
                .collect(Collectors.toList());
    }

}
