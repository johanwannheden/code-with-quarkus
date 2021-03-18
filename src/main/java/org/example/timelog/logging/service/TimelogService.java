package org.example.timelog.logging.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.example.timelog.CallContext;
import org.example.timelog.logging.model.TimelogEntity;

@ApplicationScoped
public class TimelogService {
    @Inject
    EntityManager em;

    @Inject
    CallContext callContext;

    @Transactional
    public TimelogEntity persistEntry(@Valid TimelogEntity entry) {
        var query = em.createQuery("select count(t) from TimelogEntity t where t.date = :date");
        query.setParameter("date", entry.getDate());
        var count = (long) query.getSingleResult();
        if (count > 0) {
            throw new IllegalArgumentException("Entry for date already exists: " + entry.getDate());
        }
        return em.merge(entry);
    }

    public List<TimelogEntity> getAllEntries() {
        var currentUserId = callContext.getCurrentUserId();
        if (currentUserId == null) {
            throw new IllegalStateException("No current user");
        }
        var query = em.createQuery(//
                ""//
                + "select t "//
                + "from TimelogEntity t "//
                + "where t.userId = :userId"//
                + "   or exists ("//
                + "     select u from UserEntity u "//
                + "     where u.id = :userId "//
                + "       and u.kind = 'EMPLOYER'"//
                + "   )", TimelogEntity.class);
        query.setParameter("userId", currentUserId);
        return query.getResultList();
    }

    public List<TimelogEntity> getEntriesForTimespan(LocalDate dateFrom, LocalDate dateUntil) {
        var query = em.createQuery(//
                "" //
                + "select t " //
                + "from TimelogEntity t " //
                + "where t.date >= :dateFrom " //
                + "  and t.date <= :dateUntil",//
                TimelogEntity.class);
        query.setParameter("dateFrom", dateFrom);
        query.setParameter("dateUntil", dateUntil);
        return query.getResultList();
    }

    @Transactional
    public void updateEntry(String id, @Valid TimelogEntity entry) {
        var query = em.createQuery(//
                ""//
                + "update TimelogEntity "//
                + "  set startTime = :startTime,"//
                + "      endTime = :endTime,"//
                + "      date = :date,"//
                + "      comment = :comment,"//
                + "      dateUpdated = :dateUpdated,"//
                + "      version = version + 1"//
                + "  where id = :id");

        query.setParameter("id", id);
        query.setParameter("date", entry.getDate());
        query.setParameter("dateUpdated", LocalDateTime.now());
        query.setParameter("startTime", entry.getStartTime());
        query.setParameter("endTime", entry.getEndTime());
        query.setParameter("comment", entry.getComment());

        int result = query.executeUpdate();
        if (result != 1) {
            throw new IllegalArgumentException("Could not update entry with id: " + id);
        }
    }

    @Transactional
    public void deleteEntry(String id) {
        Query query = em.createQuery("delete from TimelogEntity t where t.id = :id");
        query.setParameter("id", id);
        var result = query.executeUpdate();
        if (result != 1) {
            throw new IllegalArgumentException("Could not delete entry with id: " + id);
        }
    }
}
