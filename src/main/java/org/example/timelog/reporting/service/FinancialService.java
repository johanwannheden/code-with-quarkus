package org.example.timelog.reporting.service;

import java.time.LocalDate;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.example.timelog.logging.model.TimelogEntity;
import org.example.timelog.logging.service.TimelogService;
import org.example.timelog.reporting.finance.MonthlySalaryReport;
import org.example.timelog.reporting.finance.SalaryCalculator;
import org.example.timelog.reporting.model.GenerationContext;
import org.example.timelog.reporting.util.FinancialConstants;

@ApplicationScoped
public class FinancialService {

    final TimelogService timelogService;
    final SalaryService salaryService;
    private final FinancialConstants financialConstants;

    @Inject
    FinancialService(TimelogService timelogService, SalaryService salaryService, FinancialConstants financialConstants) {
        this.timelogService = timelogService;
        this.salaryService = salaryService;
        this.financialConstants = financialConstants;
    }

    public MonthlySalaryReport calculateSalary(GenerationContext context) {
        var timelogEntries = getTimelogEntries(context);
        // TODO wage does not necessarily change on the first day of a month
        var hourlyWage = salaryService.getHourlyWage(context.getEmployee().getId(), LocalDate.of(context.getYear(), context.getMonth(), 1));
        return new SalaryCalculator().calculate(context.getEmployee(), timelogEntries, hourlyWage, financialConstants);
    }

    private List<TimelogEntity> getTimelogEntries(GenerationContext context) {
        var from = LocalDate.of(context.getYear(), context.getMonth(), 1);
        var until = LocalDate.of(context.getYear(), context.getMonth(), from.lengthOfMonth());
        return timelogService.getEntriesForTimespan(from, until, context.getEmployee().getId());
    }
}
