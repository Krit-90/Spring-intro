package com.gleb.springintrodiction.service;

import com.gleb.springintrodiction.dto.CarDto;
import com.gleb.springintrodiction.dto.CarsListDto;
import org.springframework.stereotype.Service;

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
    public List<CarDto> getCarsByModelAndYear(String model, Integer year) {
        return carDtoDB.stream().filter(carDto -> carDto.getModel().equals(model) &&
                carDto.getYear().equals(year))
                .collect(Collectors.toList());
    }

    // TODO: Строковое поле "model" - плохой кандидат, чтобы на его основании искать объект для обновления. Лучше подойдет id - OK
    // TODO: Обновление должно работать так: на вход приходит объект машины,
    //  ты берешь оттуда все не null поля и обновляешь их в конечной сущности, которую ты нашел по id - OK
    @Override
    public boolean updateCar(Integer id, CarDto c) {
        // TODO: Форматируй код - OK
        if (isContainCar(id)) {
            CarDto searchingCarDto = carDtoDB.stream().filter(carDto -> carDto.getId().equals(id)).findFirst().get();
            if (c.getModel() != null) {
                searchingCarDto.setModel(c.getModel());
            }
            if (c.getYear() != null) {
                searchingCarDto.setYear(c.getYear());
            }
            return true;
            // TODO: else можно опустить, можно почитать вот тут на эту тему: https://habr.com/ru/post/348074/ - OK
        }
        return false;
    }

    // TODO: По аналогии, удаляй на основании id - OK
    @Override
    public boolean removeCar(Integer id) {
        return carDtoDB.removeIf(carDto -> carDto.getId().equals(id));
    }

    // TODO: private методы всегда в самом низу класса - OK
    // TODO: Этот код используется один раз, возможно нет смысла выносить в отдельный метод - OK
    // TODO: Я хотел улучшить читабельность кода, а то слишком длинная строчка в условии
    private boolean isContainCar(Integer id) {
        return carDtoDB.stream().anyMatch(carDto -> carDto.getId().equals(id));
    }
}
