package com.gleb.springintrodiction.repository;

import com.gleb.springintrodiction.data.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    List<Owner> findOwnersByFirstNameAndLastName(String firstName, String lastName);

    @Query(value = "SELECT * FROM Owners o WHERE o.id = id", nativeQuery = true)
    Owner findOwnersById(Long id);

    @Query(value = "UPDATE owners SET first_name = ?1 last_name = ?2 WHERE id=?3", nativeQuery = true)
    void update(String firstName, String lastName, Long id);
}
