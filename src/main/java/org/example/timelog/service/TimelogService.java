package org.example.timelog.service;

import org.example.timelog.model.Timelog;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class TimelogService {
    @Inject
    EntityManager em;

    @Transactional
    public void persistEntry(@Valid Timelog entry) {
        em.persist(entry);
    }

    public List<Timelog> getAllEntries() {
        return em.createQuery("select t from Timelog t", Timelog.class).getResultList();
    }

    @Transactional
    public void updateEntry(Timelog entry) {
        var query = em.createQuery("" +
                "update Timelog " +
                "  set startTime = :startTime," +
                "      endTime = :endTime," +
                "      date = :date," +
                "      comment = :comment," +
                "      dateUpdated = :dateUpdated," +
                "      version = version + 1" +
                "  where id = :id");

        query.setParameter("id", entry.getId());
        query.setParameter("date", entry.getDate());
        query.setParameter("dateUpdated", LocalDateTime.now());
        query.setParameter("startTime", entry.getStartTime());
        query.setParameter("endTime", entry.getEndTime());
        query.setParameter("comment", entry.getComment());

        int result = query.executeUpdate();
        if (result != 1) {
            throw new IllegalArgumentException("Could not update entry with id: " + entry.getId());
        }
    }
}
