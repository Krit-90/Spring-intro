package com.gleb.springintrodiction.repository;

import com.gleb.springintrodiction.data.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(value = "SELECT role_name FROM roles inner join users_roles ON  roles.id = users_roles.role_id " +
            "inner join users on users_roles.user_id = users.id where users.id = ?", nativeQuery = true)
    List<String> getRoleUser(Long id);
}
