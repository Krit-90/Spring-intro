package com.gleb.springintrodiction.repository;

import com.gleb.springintrodiction.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

@Repository
@Transactional
public class UserRepository {

    @Autowired
    private EntityManager entityManager;

    public User findByUsername(String username) {
        try {
            String sql = "Select u from " + User.class.getName() + " u "
                    + " Where u.username = :username ";

            Query query = entityManager.createQuery(sql, User.class);
            query.setParameter("username", username);

            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}