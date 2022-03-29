package org.example.timelog.reporting.service;

import org.example.timelog.reporting.finance.MonthlySalaryReport;
import org.example.timelog.reporting.generator.MonthlyBalanceGenerator;
import org.example.timelog.reporting.model.GenerationContext;
import org.example.timelog.reporting.model.GenerationReport;
import org.example.timelog.reporting.model.UserKind;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@ApplicationScoped
public class ReportingService {

    final UserService userService;
    final FinancialService financialService;
    final MonthlyBalanceGenerator generator;

    @Inject
    ReportingService(UserService userService, FinancialService financialService, MonthlyBalanceGenerator generator) {
        this.userService = userService;
        this.financialService = financialService;
        this.generator = generator;
    }

    public GenerationReport generateMonthlyReport(int year, int month, String userId) {
        var employee = userService.getUserById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + userId));
        if (UserKind.EMPLOYEE != employee.getKind()) {
            throw new IllegalArgumentException("Can only generate report for employee");
        }
        var employer = userService.getUserById(employee.getEmployerId()).orElseThrow(() -> new IllegalStateException("Invalid employer id: " + employee.getEmployerId()));

        var context = GenerationContext.builder()
                .month(month)
                .year(year)
                .employee(employee)
                .employer(employer)
                .build();
        MonthlySalaryReport monthlySalaryReport = financialService.calculateSalary(context);
        return new GenerationReport.Builder()
                .withData(generator.generateMonthlyReport(context, monthlySalaryReport))
                .withFilename(getFilename(context))
                .build();
    }

    private String getFilename(GenerationContext context) {
        var stamp = DateTimeFormatter.ofPattern("LLLL yyyy")
                .withLocale(new Locale("de", "ch"))
                .format(LocalDate.of(context.getYear(), context.getMonth(), 1));
        return "Lohnabrechnung" + ' ' + stamp;
    }

}
