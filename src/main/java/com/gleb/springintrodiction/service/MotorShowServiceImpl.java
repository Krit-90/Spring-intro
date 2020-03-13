package com.gleb.springintrodiction.service;

import com.gleb.springintrodiction.data.MotorShow;
import com.gleb.springintrodiction.dto.MotorShowDto;
import com.gleb.springintrodiction.repository.MotorShowRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class MotorShowServiceImpl implements MotorShowService {

    @Autowired
    MotorShowRepository motorShowRepository;
    @Autowired
    CarsService carsService;

    @Override
    public List<MotorShowDto> getMotorShowDtoDB() {
        return motorShowRepository.findAll().stream()
                .map(motorShow -> new MotorShowDto(motorShow.getTitle(), motorShow.getCity(),
                        carsService.mapCarToCarDto(motorShow.getCars())))
                .collect(Collectors.toList());
    }

    @Override
    public void addMotorShow(MotorShowDto motorShowDto) {
        motorShowRepository.save(new MotorShow(motorShowDto.getTitle(), motorShowDto.getCity()));
    }

    @Override
    public List<MotorShowDto> getMotorShowByTitleAndCity(String title, String city) {
        return mapMotorShowToMotorShowDto(motorShowRepository.findByTitleAndCity(title, city));
    }

    @Override
    public boolean updateMotorShow(Long id, MotorShowDto motorShowDto) {
        boolean isExist = motorShowRepository.existsById(id);
        if (isExist) {
            motorShowRepository.update(motorShowDto.getTitle(), motorShowDto.getCity(), id);
        }
        return isExist;
    }

    @Override
    public boolean removeMotorShow(Long id) {
        boolean isExist = motorShowRepository.existsById(id);
        if (isExist) {
            motorShowRepository.deleteById(id);
        }
        return isExist;
    }

    @Override
    public List<MotorShowDto> mapMotorShowToMotorShowDto(List<MotorShow> motorShowList) {
        return motorShowList.stream().map(motorShow -> new MotorShowDto(motorShow.getTitle(), motorShow.getCity(),
                carsService.mapCarToCarDto(motorShow.getCars())))
               .collect(Collectors.toList());
    }

}