package com.gleb.springintrodiction.repository;

import com.gleb.springintrodiction.data.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    List<Owner> findOwnersByFirstNameAndLastName(String firstName, String lastName);

}
