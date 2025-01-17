package com.gleb.springintrodiction.service;

import com.gleb.springintrodiction.data.Owner;
import com.gleb.springintrodiction.dto.OwnerDto;
import com.gleb.springintrodiction.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private CarsService carsService;

    @Override
    public void addOwner(OwnerDto ownerDto) {
        ownerRepository.save(new Owner(ownerDto.getFirstName(), ownerDto.getLastName()));
    }

    @Override
    public List<OwnerDto> getOwnerByFirstNameAndLastName(OwnerDto ownerDto) {
        if(ownerDto.getFirstName() == null & ownerDto.getLastName() == null & ownerDto.getCarsDtosModel() == null) {
            return mapOwnerToOwnerDto(ownerRepository.findAll());
        }
        return mapOwnerToOwnerDto(ownerRepository.findOwnersByFirstNameAndLastName(ownerDto.getFirstName(),
                ownerDto.getLastName()));
    }

    @Transactional
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
                carsService.carsModelStringList(owner.getCars()))).collect(Collectors.toList());
    }

}
