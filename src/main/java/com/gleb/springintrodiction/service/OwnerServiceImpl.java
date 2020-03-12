package com.gleb.springintrodiction.service;

import com.gleb.springintrodiction.data.Owner;
import com.gleb.springintrodiction.dto.OwnerDto;
import com.gleb.springintrodiction.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    OwnerRepository ownerRepository;
    @Autowired
    CarsService carsService;

    @Override
    public List<OwnerDto> getOwnerDtoDB() {
        return ownerRepository.findAll().stream()
                .map(owner -> new OwnerDto(owner.getFirstName(), owner.getLastName(),
                 carsService.mapCarToCarDto(owner.getCars())))
                .collect(Collectors.toList());
    }

    @Override
    public void addOwner(OwnerDto ownerDto) {
        ownerRepository.save(new Owner(ownerDto.getFirstName(), ownerDto.getLastName(), carsService
                .mapCarDtoToCar(ownerDto.getCarsDto())));
    }

    @Override
    public List<OwnerDto> getOwnerByFirstNameAndLastName(String firstName, String lastName) {
        return mapOwnerToOwnerDto(ownerRepository.findOwnersByFirstNameAndLastName(firstName, lastName));
    }

    @Override
    public boolean updateOwner(Long id, OwnerDto ownerDto) {
        boolean isExist = ownerRepository.existsById(id);
        if (isExist) {
            ownerRepository.update(ownerDto.getFirstName(), ownerDto.getLastName(), id);
        }
        return isExist;
    }

    @Override
    public boolean removeOwner(Long id) {
        boolean isExist = ownerRepository.existsById(id);
        if (isExist) {
        ownerRepository.deleteById(id);
        }
        return isExist;
    }

    @Override
    public List<OwnerDto> mapOwnerToOwnerDto(List<Owner> ownerList) {
        return ownerList.stream().map(owner -> new OwnerDto(owner.getFirstName(), owner.getLastName(),
                carsService.mapCarToCarDto(owner.getCars()))).collect(Collectors.toList());
    }

    @Override
    public List<Owner> mapOwnerDtoToOwner(List<OwnerDto> ownerDtoList) {
        return ownerDtoList.stream().map(ownerDto -> new Owner(ownerDto.getFirstName(), ownerDto.getLastName(),
                carsService.mapCarDtoToCar(ownerDto.getCarsDto())))
                .collect(Collectors.toList());
    }


}
