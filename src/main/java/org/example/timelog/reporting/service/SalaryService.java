package org.example.timelog.reporting.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Optional;

@ApplicationScoped
public class SalaryService {

    @Inject
    EntityManager em;

    public double getHourlyWage(String userId, LocalDate date) {
        var query = em.createQuery("" +
                "SELECT s.hourlyWage " +
                "FROM SalaryEntity s " +
                "WHERE s.userId = :userId " +
                "  AND s.validFrom <= :date " +
                "ORDER BY s.validFrom DESC");
        query.setParameter("userId", userId);
        query.setParameter("date", date);
        query.setMaxResults(1);
        Double singleResult = (Double) query.getSingleResult();
        return Optional.ofNullable(singleResult)
                .orElseThrow(() -> new IllegalArgumentException("No such user: " + userId));
    }
}
