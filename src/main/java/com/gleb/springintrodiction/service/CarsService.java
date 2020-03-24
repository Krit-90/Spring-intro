package com.gleb.springintrodiction.service;

import java.util.List;
import com.gleb.springintrodiction.data.Car;
import com.gleb.springintrodiction.data.Owner;
import com.gleb.springintrodiction.dto.CarDto;

public interface CarsService {

    /**
     * Метод добавления машины в БД
     *
     * @param carDto ДТО машины
     */
    void addCar(CarDto carDto);

    /**
     * Метод добавления автосалона для конкретной машины
     *
     * @param motorShowId Id автосалона
     * @param id Id машины
     * @return  true, если такой автосалон существует, false в противном случае
     */
    boolean addMotorShowToCarById(Long motorShowId, Long id);

    /**
     * Метод добавления владельца для конкретной машины
     *
     * @param ownerId Id автосалона
     * @param id Id машины
     * @return  true, если такой автосалон существует, false в противном случае
     */
    boolean addOwnerToCarById(Long ownerId, Long id);

    /**
     * Метод получения списка машин соответствующей модели и года
     *
     * @param carDto Дто машины, из которой мы берем искомые данные
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
    boolean updateCar(Long id, CarDto c);

    /**
     * Метод удаления машины из БД
     *
     * @param id Id искомой машины машины
     * @return true, если удаление прошло удчано, false в противном случае
     */
    boolean removeCar(Long id);

    /**
     * Метод маппинга списка машин в список без закрытой информации(например id)
     *
     * @param carList Список машин
     * @return Список для передачи клиенту
     */
    List<CarDto> mapCarToCarDto(List<Car> carList);

    /**
     * Метод быборки моделей из приведенного списка машин
     * @param carList Список машин для выборки
     * @return Список для передачи связанных Дто
     */

    List<String> carsModelStringList(List<Car> carList);
    /**
     * Метод для теста, который возвращает список владельцев машин определенной модели и года, если год 1967,
     * то вернуть первого владельца, если 1970, то последнего, в противном случае вернуть всех владельцев
     * @param carDto Дто искомой машины
     * @return Список владельцев по условию
     */
    List<Owner> findOwnersOfCarsByAge(CarDto carDto);
}
