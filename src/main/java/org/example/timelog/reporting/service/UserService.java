package org.example.timelog.reporting.service;

import org.example.timelog.reporting.model.UserEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Optional;

@ApplicationScoped
public class UserService {

    @Inject
    EntityManager em;

    public Optional<UserEntity> getUserById(String userId) {
        return Optional.ofNullable(em.find(UserEntity.class, userId));
    }
}
