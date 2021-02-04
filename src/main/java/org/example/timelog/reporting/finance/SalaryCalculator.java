package org.example.timelog.reporting.finance;

import org.example.timelog.logging.model.TimelogEntity;
import org.example.timelog.reporting.util.FinancialConstants;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public final class SalaryCalculator {

    public final MonthlySalaryReport calculate(List<TimelogEntity> timelogEntries, double hourlyWage, FinancialConstants constants) {
        double hoursWorked = getTotalNumberOfHoursWorked(timelogEntries);

        double grossSalary = hoursWorked * hourlyWage;
        double hourlyWageExcludingHolidayExpense = hourlyWage - (hourlyWage * constants.getHolidayExpense());
        double wageForHoursWorked = hourlyWageExcludingHolidayExpense * hoursWorked;
        double amountHolidayExpense = constants.getHolidayExpense() * grossSalary;
        double amountAhvIvEo = grossSalary * constants.getAhvIvEo();
        double amountAlv = grossSalary * constants.getAlv();
        double amountNbu = grossSalary * constants.getNbu();
        double amountQuellensteuer = grossSalary * constants.getQuellensteuer();
        double totalSocialReductions = amountAhvIvEo + amountAlv + amountNbu;
        double netSalary = grossSalary - totalSocialReductions - amountQuellensteuer;
        double totalReductions = totalSocialReductions + amountQuellensteuer;

        var hoursWorkedByDay = timelogEntries.stream()
                .collect(
                        Collectors.toMap(
                                TimelogEntity::getDate,
                                it -> durationToHours(Duration.between(it.getStartTime(), it.getEndTime()))));

        return new MonthlySalaryReport.MonthlySalaryReportBuilder()
                .numberOfHoursWorked(hoursWorked)
                .wageForHoursWorked(wageForHoursWorked)
                .hourlyWageExcludingHolidayExpense(hourlyWageExcludingHolidayExpense)
                .amountHolidayExpense(amountHolidayExpense)
                .amountAhvIvEo(amountAhvIvEo)
                .amountAlv(amountAlv)
                .amountNbu(amountNbu)
                .amountQuellensteuer(amountQuellensteuer)
                .grossSalary(grossSalary)
                .netSalary(netSalary)
                .totalReductions(totalReductions)
                .totalSocialReductions(totalSocialReductions)
                .hoursWorkedByDay(hoursWorkedByDay)
                .build();
    }

    private double getTotalNumberOfHoursWorked(List<TimelogEntity> timelogEntries) {
        return timelogEntries.stream()
                .map(it -> Duration.between(it.getStartTime(), it.getEndTime()))
                .reduce(Duration::plus)
                .map(this::durationToHours)
                .orElse(0d);
    }

    private double durationToHours(Duration duration) {
        return duration.getSeconds() / (60d * 60d);
    }
}
