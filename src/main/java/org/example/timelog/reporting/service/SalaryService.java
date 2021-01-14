package org.example.timelog.reporting.service;

import org.example.timelog.reporting.model.SalaryEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Optional;

@ApplicationScoped
public class SalaryService {

    @Inject
    EntityManager em;

    public double getHourlyWage(String userId) {
        return Optional.ofNullable(em.find(SalaryEntity.class, userId))
                .map(SalaryEntity::getHourlyWage)
                .orElseThrow(() -> new IllegalArgumentException("No such user: " + userId));
    }
}
