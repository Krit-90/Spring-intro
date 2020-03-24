package com.gleb.springintrodiction.service;

import com.gleb.springintrodiction.data.Car;
import com.gleb.springintrodiction.data.MotorShow;
import com.gleb.springintrodiction.data.Owner;
import com.gleb.springintrodiction.dto.CarDto;
import com.gleb.springintrodiction.repository.CarRepository;
import com.gleb.springintrodiction.repository.MotorShowRepository;
import com.gleb.springintrodiction.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
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
        if (carDto.getModel() == null & carDto.getYear() == null & carDto.getMotorShowTitle() == null) {
            return mapCarToCarDto(carRepository.findAll());
        }
        Car car;
        if (carDto.getMotorShowTitle() != null) {
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
        List<CarDto> carDtos;
        carDtos = carList.stream().filter(car -> car.getMotorShow() == null).
        map(car -> new CarDto(car.getModel(), car.getYear())).collect(Collectors.toList());
        carDtos.addAll(carList.stream().filter(car -> car.getMotorShow() != null).
                map(car -> new CarDto(car.getModel(), car.getYear(), car.getMotorShow().getTitle()))
                .collect(Collectors.toList()));
        return carDtos;
    }

    public List<String> carsModelStringList(List<Car> carList) {
        return carList.stream().map(Car::getModel).collect(Collectors.toList());
    }

    public List<Owner> findOwnersOfCarsByAge(CarDto carDto) {
        List<Owner> searchingList = new ArrayList<>();
        if (carDto.getModel() == null & carDto.getYear() == null) {
            searchingList.add(new Owner("unknown", "car"));
            return searchingList;
        } else {
            if (carDto.getYear() == null & carDto.getModel() != null) {
                searchingList.add(new Owner("not enough", "info"));
                return searchingList;
            }
        }
        for (Car car : carRepository.findAll()) {
            if (car.getModel().equals(carDto.getModel()) & car.getYear().equals(carDto.getYear())) {
                switch (car.getYear()) {
                    case 1967: searchingList.add(car.getOwners().get(0));
                    break;
                    case 1970: searchingList.add(car.getOwners().get(car.getOwners().size() - 1));
                    break;
                    default: searchingList.addAll(car.getOwners());
                }
            }
        }
        return searchingList;
    }
}
