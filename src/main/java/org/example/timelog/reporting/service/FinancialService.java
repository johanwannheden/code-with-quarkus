package org.example.timelog.reporting.service;

import org.example.timelog.logging.model.TimelogEntity;
import org.example.timelog.logging.service.TimelogService;
import org.example.timelog.reporting.finance.SalaryCalculator;
import org.example.timelog.reporting.model.GenerationContext;
import org.example.timelog.reporting.finance.MonthlySalaryReport;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class FinancialService {

    final TimelogService timelogService;
    final SalaryService salaryService;

    @Inject
    FinancialService(TimelogService timelogService, SalaryService salaryService) {
        this.timelogService = timelogService;
        this.salaryService = salaryService;
    }

    public MonthlySalaryReport calculateSalary(GenerationContext context) {
        var timelogEntries = getTimelogEntries(context);
        var hourlyWage = salaryService.getHourlyWage(context.getEmployee().getId());
        return new SalaryCalculator().calculate(timelogEntries, hourlyWage);
    }

    private List<TimelogEntity> getTimelogEntries(GenerationContext context) {
        var from = LocalDate.of(context.getYear(), context.getMonth(), 1);
        var until = LocalDate.of(context.getYear(), context.getMonth(), from.lengthOfMonth());
        return timelogService.getEntriesForTimespan(from, until);
    }
}
