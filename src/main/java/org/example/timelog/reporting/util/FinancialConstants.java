package org.example.timelog.reporting.util;

import lombok.Getter;
import org.eclipse.microprofile.config.ConfigProvider;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

@ApplicationScoped
@Getter
public class FinancialConstants {

    private static final String PREFIX = "timelog.finance.social.expense";

    private Double holidayExpense;
    private Double ahvIvEo;
    private Double alv;
    private Double nbu;
    private Double quellensteuer;

    private static final Locale locale = new Locale.Builder().setLanguage("de").setRegion("CH").build();

    @PostConstruct
    public void postConstruct() {
        var config = ConfigProvider.getConfig();
        holidayExpense = config.getValue(PREFIX + ".holidays", Double.class);
        ahvIvEo = config.getValue(PREFIX + ".ahv", Double.class);
        alv = config.getValue(PREFIX + ".alv", Double.class);
        nbu = config.getValue(PREFIX + ".nbu", Double.class);
        quellensteuer = config.getValue(PREFIX + ".quellensteuer", Double.class);
    }

    public static String getPercentageLabel(double percentageValue) {
        var df = new DecimalFormat("#.000%", DecimalFormatSymbols.getInstance(locale));
        return df.format(percentageValue);
    }
}
