package org.example.timelog.user.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class UserService {

    @Inject
    EntityManager em;

    public String getIdOfSingleEmployee() {
        // FIXME this is temporary: there is currently one employee only
        var query = em.createQuery("select u.id from UserEntity u where u.kind = 'EMPLOYEE'");
        Object singleResult = query.getSingleResult();
        return String.valueOf(singleResult);
    }
}
