package com.gleb.springintrodiction.service;

import com.gleb.springintrodiction.data.MotorShow;
import com.gleb.springintrodiction.dto.MotorShowDto;

import java.util.List;

public interface MotorShowService {

    /**
     * Метод добавления автосалона в БД
     *
     * @param motorShowDto ДТО автосалона
     */
    void addMotorShow(MotorShowDto motorShowDto);

    /**
     * Метод получения списка автосалононов с соответствующими названием и городом
     *
     * @param motorShowDto Дто автосалона, из которого берем данные
     * @return Отфильтрованный список автосалонов
     */
    List<MotorShowDto> getMotorShowByTitleAndCity(MotorShowDto motorShowDto);

    /**
     * *Метод изменения названия и/или города автосалона
     *
     * @param id       Id искомого автосалона
     * @param motorShowDto Объект автосалона от пользователя, данные которой будем подставлять
     * @return true, если данный автосалон есть в БД и обновление прошло успешно, false в противном случае
     */
    boolean updateMotorShow(Long id, MotorShowDto motorShowDto);

    /**
     * Метод удаления автосалона из БД
     *
     * @param id Id искомой автосалона
     * @return true, если удаление прошло удчано, false в противном случае
     */
    boolean removeMotorShow(Long id);

    /**
     * Метод маппинга списка автосалонов в список без закрытой информации(например id)
     *
     * @param motorShowList Список автосалонов
     * @return Список для передачи пользователю
     */
    List<MotorShowDto> mapMotorShowToMotorShowDto(List<MotorShow> motorShowList);

}
