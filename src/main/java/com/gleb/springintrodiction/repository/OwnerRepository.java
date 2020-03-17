package com.gleb.springintrodiction.repository;

import com.gleb.springintrodiction.data.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    List<Owner> findOwnersByFirstNameAndLastName(String firstName, String lastName);

    @Query(value = "SELECT * FROM Owners o WHERE o.id = id", nativeQuery = true)
    Owner findOwnersById(Long id);
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE Owners  SET first_name = ?, last_name = ? WHERE id=?", nativeQuery = true)
    void update(String firstName, String lastName, Long id);
}
