package com.gleb.springintrodiction.service;

import java.util.List;

import com.gleb.springintrodiction.dto.CarDto;

public interface CarsService {

    /**
     * Метод получения всего всписка машин
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
     * @param year  Год выпуска модели
     * @param model Модель машины
     * @return Отфильтрованный список машин
     */
    List<CarDto> getCarsByModelAndYear(String model, Integer year);

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
}
