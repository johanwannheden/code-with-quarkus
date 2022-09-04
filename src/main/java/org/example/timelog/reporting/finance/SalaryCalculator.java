package org.example.timelog.reporting.finance;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.example.timelog.logging.model.TimelogEntity;
import org.example.timelog.reporting.model.UserEntity;
import org.example.timelog.reporting.util.FinancialConstants;

public final class SalaryCalculator {

    public MonthlySalaryReport calculate(UserEntity user, List<TimelogEntity> timelogEntries, double hourlyWage,
            FinancialConstants constants) {
        double hoursWorked = getTotalNumberOfHoursWorked(timelogEntries);

        var withNbu = Boolean.TRUE.equals(user.isNbuRequired());
        var withQuellensteuer = Boolean.TRUE.equals(user.isQuellensteuerRequired());

        var grossSalary = hoursWorked * hourlyWage;
        var hourlyWageExcludingHolidayExpense = hourlyWage - (hourlyWage * constants.getHolidayExpense());
        var wageForHoursWorked = hourlyWageExcludingHolidayExpense * hoursWorked;
        var amountHolidayExpense = constants.getHolidayExpense() * grossSalary;
        var amountAhvIvEo = grossSalary * constants.getAhvIvEo();
        var amountAlv = grossSalary * constants.getAlv();
        var amountNbu = withNbu ? grossSalary * constants.getNbu() : 0.0d;
        var amountQuellensteuer = withQuellensteuer ? grossSalary * constants.getQuellensteuer() : 0.0d;
        var totalSocialReductions = amountAhvIvEo + amountAlv + amountNbu;
        var netSalary = grossSalary - totalSocialReductions - amountQuellensteuer;
        var totalReductions = totalSocialReductions + amountQuellensteuer;

        var hoursWorkedByDay = timelogEntries.stream()
                .collect(Collectors.toMap(TimelogEntity::getDate,
                        it -> durationToHours(Duration.between(it.getStartTime(), it.getEndTime()))));

        return new MonthlySalaryReport.MonthlySalaryReportBuilder().numberOfHoursWorked(hoursWorked)
                .wageForHoursWorked(wageForHoursWorked)
                .hourlyWageExcludingHolidayExpense(hourlyWageExcludingHolidayExpense)
                .isNbu(withNbu)
                .isQuellensteuer(Boolean.TRUE.equals(user.isQuellensteuerRequired()))
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
