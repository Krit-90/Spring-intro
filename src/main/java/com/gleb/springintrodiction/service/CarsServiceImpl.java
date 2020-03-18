package com.gleb.springintrodiction.service;

import com.gleb.springintrodiction.data.Car;
import com.gleb.springintrodiction.data.MotorShow;
import com.gleb.springintrodiction.dto.CarDto;
import com.gleb.springintrodiction.dto.MotorShowDto;
import com.gleb.springintrodiction.repository.CarRepository;
import com.gleb.springintrodiction.repository.MotorShowRepository;
import com.gleb.springintrodiction.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarsServiceImpl implements CarsService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private MotorShowRepository motorShowRepository;
    @Autowired
    private OwnerRepository ownerRepository;


    public List<CarDto> getCarsDtoDB() {
        return mapCarToCarDto(carRepository.findAll());
    }

    @Override
    public void addCar(CarDto carDto) {
        carRepository.save(new Car(carDto.getModel(), carDto.getYear()));
    }

    public boolean addMotorShowToCarById(Long motorShowId, Long id) {
        boolean isExistMotorShow = motorShowRepository.findById(motorShowId).isPresent();
        if (isExistMotorShow) {
            carRepository.updateMotorShowId(motorShowId, id);
        }
        return isExistMotorShow;
    }

    public boolean addOwnerToCarById(Long ownerId, Long id) {
        boolean isExistOwner = ownerRepository.findById(ownerId).isPresent();
        if (isExistOwner) {
            carRepository.updateOwnerId(ownerId, id);
        }
        return isExistOwner;
    }

    @Override
    public List<CarDto> getCarsByModelAndYear(CarDto carDto) {
        if (carDto == null){
            List<Car> result = carRepository.findAll();
            return result.stream().map(car1 -> new CarDto(car1.getModel(), car1.getYear(),
                    car1.getMotorShow().getTitle())).collect(Collectors.toList());
        }
        Car car;
        if(carDto.getMotorShowTitle() != null) {
            MotorShow motorShow = motorShowRepository.findByTitle(carDto.getMotorShowTitle()).get(0);
            car = new Car(carDto.getModel(), carDto.getYear(), motorShow);
        } else {
            car = new Car(carDto.getModel(), carDto.getYear());
        }
        List<Car> result = carRepository.findAll(Example.of(car));
        return mapCarToCarDto(result);
    }

    @Override
    public boolean updateCar(Long id, CarDto c) {
        if (carRepository.existsById(id)) {
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
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
        }
        return false;
    }

    @Override
    public List<CarDto> mapCarToCarDto(List<Car> carList) {
        return carList.stream().map(car -> new CarDto(car.getModel(), car.getYear(), car.getMotorShow().getTitle()))
                .collect(Collectors.toList());
    }

    public List<String> carsModelStringList(List<Car> carList){
        return carList.stream().map(car -> new String(car.getModel())).collect(Collectors.toList());
    }


}
