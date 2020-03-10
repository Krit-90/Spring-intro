package com.gleb.springintrodiction.repository;

import com.gleb.springintrodiction.data.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

}
