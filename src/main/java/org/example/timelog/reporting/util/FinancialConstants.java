package org.example.timelog.reporting.util;

import java.text.DecimalFormat;

public final class FinancialConstants {
    public static final double HOLIDAY_EXPENSE = 0.08333d;
    public static final double AHV_IV_EO = 0.05275d;
    public static final double ALV = 0.011d;
    public static final double NBU = 0.016d;
    public static final double QUELLENSTEUER = 0.05d;

    public static String getPercentageLabel(double percentageValue) {
        var df = new DecimalFormat("#.000%");
        return df.format(percentageValue);
    }
}
