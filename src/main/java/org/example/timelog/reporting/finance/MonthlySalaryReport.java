package org.example.timelog.reporting.finance;

import java.time.LocalDate;
import java.util.Map;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(access = AccessLevel.PUBLIC)
public class MonthlySalaryReport {
    double numberOfHoursWorked;

    double hourlyWageExcludingHolidayExpense;

    /* Excludes holiday expenses. */
    double wageForHoursWorked;

    double amountHolidayExpense;

    /* Bruttolohn = Stundenlohn + Ferienvergütung */
    double grossSalary;

    double amountAhvIvEo;
    double amountAlv;
    double amountNbu;
    double totalSocialReductions;

    boolean isNbu;
    boolean isQuellensteuer;

    double amountQuellensteuer;

    /* Abzüge = Quellensteuer + Total Sozialversicherungsabzüge */
    double totalReductions;

    /* Nettolohn = Bruttolohn - Abzüge */
    double netSalary;

    Map<LocalDate, Double> hoursWorkedByDay;
}
