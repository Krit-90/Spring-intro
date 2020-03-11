package com.gleb.springintrodiction.repository;

import com.gleb.springintrodiction.data.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {

}
