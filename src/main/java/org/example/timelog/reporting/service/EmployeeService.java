package org.example.timelog.reporting.service;

import org.example.timelog.reporting.model.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class EmployeeService {

    @Inject
    EntityManager em;

    @Transactional
    public void persistEmployee(Employee employee) {
        em.persist(employee);
    }

}
