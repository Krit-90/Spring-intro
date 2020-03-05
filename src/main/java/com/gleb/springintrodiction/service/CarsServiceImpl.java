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
    // TODO: Юзай diamond operator, чтобы не возникало предупреждения
    // TODO: Название переменной странное
    // TODO: Зачем тебе два списка машин?
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


    // TODO: Чет лишнего напридумывал. Все методы сервиса должны быть описаны в интерфейсе и не должны быть статичными
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

    // TODO: Строковое поле "model" - плохой кандидат, чтобы на его основании искать объект для обновления. Лучше подойдет id
    // TODO: Обновление должно работать так: на вход приходит объект машины, ты берешь оттуда все не null поля и обновляешь их в конечной сущности, которую ты нашел по id
    @Override
    public boolean updateCar(String model, Integer year) {
        // TODO: Форматируй код
        if (isContainCar(model)) {
        carsDB.stream().filter(cars -> cars.getModel().equals(model))
                .findFirst().get().setYear(year);
        return true;
        // TODO: else можно опустить, можно почитать вот тут на эту тему: https://habr.com/ru/post/348074/
        } else {
            return false;
        }
    }

    // TODO: private методы всегда в самом низу класса
    // TODO: Этот код используется один раз, возможно нет смысла выносить в отдельный метод
    private boolean isContainCar(String model) {
        return carsDB.stream().anyMatch(cars -> cars.getModel().equals(model));
    }

    // TODO: По аналогии, удаляй на основании id
    @Override
    public boolean removeCar(String model) {
        return carsDB.removeIf(cars -> cars.getModel().equals(model));
    }
}
