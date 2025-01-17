package com.gleb.springintrodiction.service;

import com.gleb.springintrodiction.data.MotorShow;
import com.gleb.springintrodiction.dto.MotorShowDto;
import com.gleb.springintrodiction.repository.MotorShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MotorShowServiceImpl implements MotorShowService {

    @Autowired
    private MotorShowRepository motorShowRepository;
    @Autowired
    private CarsService carsService;

    @Override
    public void addMotorShow(MotorShowDto motorShowDto) {
        motorShowRepository.save(new MotorShow(motorShowDto.getTitle(), motorShowDto.getCity()));
    }

    @Override
    public List<MotorShowDto> getMotorShowByTitleAndCity(MotorShowDto motorShowDto) {
        if (motorShowDto.getTitle() == null & motorShowDto.getCity() == null) {
            return mapMotorShowToMotorShowDto(motorShowRepository.findAll());
        }
        return mapMotorShowToMotorShowDto(motorShowRepository.findByTitleAndCity(motorShowDto.getTitle(),
                motorShowDto.getCity()));
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
                carsService.carsModelStringList(motorShow.getCars())))
                .collect(Collectors.toList());
    }
}
