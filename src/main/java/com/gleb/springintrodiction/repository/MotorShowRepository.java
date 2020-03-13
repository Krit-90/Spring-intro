package com.gleb.springintrodiction.repository;

import com.gleb.springintrodiction.data.MotorShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MotorShowRepository extends JpaRepository<MotorShow, Long> {
    List<MotorShow> findByTitle(String title);
    @Query(value = "SELECT * FROM MotorShow m WHERE m.title = ?1 m.city = ?2", nativeQuery = true)
    List<MotorShow> findByTitleAndCity(String title, String city);
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE motorshow  SET title = ?, city = ? WHERE id=?", nativeQuery = true)
    void update(String title, String city, Long id);
}
