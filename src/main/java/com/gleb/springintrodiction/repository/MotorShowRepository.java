package com.gleb.springintrodiction.repository;

import com.gleb.springintrodiction.data.MotorShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MotorShowRepository extends JpaRepository<MotorShow, Long> {
    List<MotorShow> findByTitle(String title);
    @Query(value = "SELECT * FROM Motor_show WHERE title = ? AND city = ?", nativeQuery = true)
    List<MotorShow> findByTitleAndCity(String title, String city);
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE Motor_show  SET title = ?, city = ? WHERE id=?", nativeQuery = true)
    void update(String title, String city, Long id);
}
