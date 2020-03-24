package com.gleb.springintrodiction.repository;

import com.gleb.springintrodiction.data.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findCarsByModel(String model);
    List<Car> findCarsByYear(Integer year);
    List<Car> findCarsByModelAndYear(String model, Integer year);

    @Query(value = "SELECT * FROM Cars WHERE id = ?", nativeQuery = true)
    Car findCarsById(Long id);
    @Query(value = "UPDATE Cars SET motor_show_id = ? WHERE id=?", nativeQuery = true)
    void updateMotorShowId(Long motorShowId, Long id);
    @Query(value = "UPDATE Owners_Cars SET owner_id = ? WHERE car_id=?", nativeQuery = true)
    void updateOwnerId(Long ownerId, Long id);
}
