package com.gleb.springintrodiction.service;

import java.util.List;

import com.gleb.springintrodiction.dto.Cars;
import com.gleb.springintrodiction.dto.CarsDto;

public interface CarsService {
    /**
     * Метод добавления машины в БД
     * @param cars ДТО машины
     * @return true, если добавлено удачно, false в противном случае
     */
    boolean addCar(Cars cars);

/*    *//**
     *Метод получения списка всех машин из БД
     * @return Список машин
     *//*
    List<CarsDto> getCarsDB();*/

    /**
     *Метод получения списка машин соответствующего года выпуска
     * @param year Год выпуска машины
     * @return Отфильтрованный список машин
     */
    List<Cars> getCarByYear(Integer year);

    /**
     * *Метод изменения года выпуска машины
     * @param model Название искомой машины
     * @param year Год выпуска, на который будем менять
     * @return true, если данная модель есть в БД, false в противном случае
     */
    boolean updateCar(String model, Integer year);

    /**
     *Метод удаления машины из БД
     * @param model Название машины
     * @return true, если удаление прошло удчано, false в противном случае
     */
    boolean removeCar(String model);
}
