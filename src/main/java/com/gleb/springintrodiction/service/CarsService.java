package com.gleb.springintrodiction.service;

import java.util.List;
import com.gleb.springintrodiction.data.Car;
import com.gleb.springintrodiction.dto.CarDto;

public interface CarsService {

    /**
     * Метод получения всего списка машин
     *
     * @return БД машин
     */
    List<CarDto> getCarsDtoDB();

    /**
     * Метод добавления машины в БД
     *
     * @param carDto ДТО машины
     * @return true, если добавлено удачно, false в противном случае
     */
    boolean addCar(CarDto carDto);

    /**
     * Метод получения списка машин соответствующего года выпуска
     *
     * @param year Год выпуска машины
     * @return Отфильтрованный список машин
     */
    List<CarDto> getCarsByYear(Integer year);

    /**
     * Метод получения списка машин соответствующей модели
     *
     * @param model Модель машины
     * @return Отфильтрованный список машин
     */
    List<CarDto> getCarsByModel(String model);

    /**
     * Метод получения списка машин соответствующей модели и года
     *
     * @param carDto Dto машины у котрой берем искомые данные
     * @return Отфильтрованный список машин
     */
    List<CarDto> getCarsByModelAndYear(CarDto carDto);

    /**
     * *Метод изменения года выпуска и/или модели машины
     *
     * @param id Id искомой машины
     * @param c  Объект машины от клиента, данные которой будем подставлять
     * @return true, если данная модель есть в БД и обновление прошло успешно, false в противном случае
     */
    boolean updateCar(Integer id, CarDto c);

    /**
     * Метод удаления машины из БД
     *
     * @param id Id искомой машины машины
     * @return true, если удаление прошло удчано, false в противном случае
     */
    boolean removeCar(Integer id);

    /**
     * Метод маппинга списка машин в список без закрытой информации(например id)
     * @param carList Список машин
     * @return Список для передачи клиенту
     */
    List<CarDto> mapCarToCarDto(List<Car> carList);

    /**
     * Метод маппинга списка машин от клиента в список для базы данных
     * @param carList Список машин владельца
     * @return Список для передачи в базу
     */
    List<Car> mapCarDtoToCar(List<CarDto> carList);
}
