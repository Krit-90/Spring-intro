package com.gleb.springintrodiction.service;

import com.gleb.springintrodiction.data.Owner;
import com.gleb.springintrodiction.dto.OwnerDto;

import java.util.List;

public interface OwnerService {
    /**
     * Метод получения всего списка владельцев машин
     *
     * @return БД владельцев
     */
    List<OwnerDto> getOwnerDtoDB();

    /**
     * Метод добавления владельца в БД
     *
     * @param ownerDto ДТО владельца
     * @return
     */
    void addOwner(OwnerDto ownerDto);

    /**
     * Метод получения списка владельцев машин с соответствующими именем и фамилией
     *
     * @param firstName Имя владельца
     * @param lastName  Фамилия владельца
     * @return Отфильтрованный список владельцев машин
     */
    List<OwnerDto> getOwnerByFirstNameAndLastName(String firstName, String lastName);

    /**
     * *Метод изменения имени и/или фамилии машины
     *
     * @param id       Id искомого владельца
     * @param ownerDto Объект владельца машины от пользователя, данные которой будем подставлять
     * @return true, если данный владелец есть в БД и обновление прошло успешно, false в противном случае
     */
    boolean updateOwner(Long id, OwnerDto ownerDto);

    /**
     * Метод удаления владельца машины из БД
     *
     * @param id Id искомога владельца
     * @return true, если удаление прошло удчано, false в противном случае
     */
    boolean removeOwner(Long id);

    /**
     * Метод маппинга списка владельцев в список без закрытой информации(например id)
     *
     * @param ownerList Список владельцев
     * @return Список для передачи пользователю
     */
    List<OwnerDto> mapOwnerToOwnerDto(List<Owner> ownerList);

}
