package com.gleb.springintrodiction.service;

import com.gleb.springintrodiction.dto.OwnerDto;

import java.util.List;

public class OwnerServiceImpl implements OwnerService {
    @Override
    public List<OwnerDto> getOwnerDtoDB() {
        return null;
    }

    @Override
    public boolean addOwner(OwnerDto ownerDto) {
        return false;
    }

    @Override
    public List<OwnerDto> getOwnerByFirstNameAndLastName(String firstName, String lastName) {
        return null;
    }

    @Override
    public boolean updateOwner(Integer id, OwnerDto ownerDto) {
        return false;
    }

    @Override
    public boolean removeOwner(Integer id) {
        return false;
    }
}
