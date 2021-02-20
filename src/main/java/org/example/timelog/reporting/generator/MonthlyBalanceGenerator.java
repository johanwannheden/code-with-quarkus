package org.example.timelog.reporting.generator;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.example.timelog.reporting.finance.MonthlySalaryReport;
import org.example.timelog.reporting.model.GenerationContext;
import org.example.timelog.reporting.model.UserEntity;
import org.example.timelog.reporting.util.FinancialConstants;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.example.timelog.reporting.util.FinancialConstants.getPercentageLabel;

@ApplicationScoped
public class MonthlyBalanceGenerator {

    private static final Logger LOGGER = Logger.getLogger(MonthlyBalanceGenerator.class);

    private static final BaseColor TABLE_CELL_BACKGROUND_COLOR = new BaseColor(240, 240, 240);

    private static final Font CELL_CONTENT_FONT = FontFactory.getFont(FontFactory.HELVETICA, 6);
    private static final Font CELL_CONTENT_ITALIC_FONT = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 6, Font.FontStyle.UNDERLINE.ordinal());
    private static final Font CELL_CONTENT_BOLD__FONT = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 6);
    private static final Font CELL_HEADER_CONTENT_FONT = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8);
    private static final Font DOCUMENT_TITLE_FONT = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);

    private final Locale locale = new Locale.Builder().setLanguage("de").setRegion("CH").build();

    private final FinancialConstants financialConstants;

    @Inject
    public MonthlyBalanceGenerator(FinancialConstants financialConstants) {
        this.financialConstants = financialConstants;
    }

    public InputStream generateMonthlyReport(GenerationContext context, MonthlySalaryReport monthlySalaryReport) {

        try {
            Document document = new Document(PageSize.A4, 20, 20, 20, 20);

            var paragraph = createTitleParagraph(context.getMonth(), context.getYear());
            var generatedAtParagraph = createGeneratedAtParagraph();
            var employerAndEmployeeTable = createEmployerAndEmployeeTable(context.getEmployee(), context.getEmployer());
            var wageTable = createWorkSummaryTable(monthlySalaryReport);
            var logTable = createLogTable(monthlySalaryReport);

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, os);
            document.open();
            document.add(generatedAtParagraph);
            document.add(paragraph);
            document.add(Chunk.NEWLINE);
            document.add(employerAndEmployeeTable);
            document.add(Chunk.NEWLINE);
            document.add(wageTable);
            document.add(Chunk.NEWLINE);
            document.add(logTable);
            document.close();

            return new ByteArrayInputStream(os.toByteArray());
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException("Generation failed", e);
        }
    }

    Paragraph createGeneratedAtParagraph() {
        var generatedAtComment = String.format("Generated at %s", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        var generatedAtParagraph = new Paragraph(new Chunk(generatedAtComment, FontFactory.getFont(FontFactory.HELVETICA, 6)));
        generatedAtParagraph.setAlignment(Element.ALIGN_RIGHT);
        return generatedAtParagraph;
    }

    Paragraph createTitleParagraph(int month, int year) {
        var documentTitle = String.format("Lohnabrechnung: %s %d", Month.of(month).getDisplayName(TextStyle.FULL, locale), year);
        var chunk = new Chunk(documentTitle, DOCUMENT_TITLE_FONT);
        return new Paragraph(chunk);
    }

    PdfPTable createEmployerAndEmployeeTable(UserEntity employee, UserEntity employer) throws DocumentException {
        PdfPTable table = new PdfPTable(3);
        int[] relativeWidths = IntStream.of(1, 2, 1).toArray();
        table.setWidths(relativeWidths);

        createBoldCell("Arbeitnehmer", "", "Arbeitgeber").forEach(table::addCell);
        createCell(
                String.format("%s %s", employee.getFirstName(), employee.getLastName()),//
                "",//
                String.format("%s %s", employer.getFirstName(), employer.getLastName()))//
                .forEach(table::addCell);
        createCell(
                String.format("%s %s", employee.getStreet(), employee.getStreetNumber()),//
                "",//
                String.format("%s %s", employer.getStreet(), employer.getStreetNumber()))//
                .forEach(table::addCell);
        createCell(
                String.format("%s %s", employee.getZip(), employee.getCity()),//
                "",//
                String.format("%s %s", employer.getZip(), employer.getCity()))//
                .forEach(table::addCell);

        return table;
    }

    PdfPTable createLogTable(MonthlySalaryReport salaryReport) throws DocumentException {
        PdfPTable table = new PdfPTable(4);
        createHeaderCells("Arbeitsstunden", "", "Datum", "Stunden").forEach(table::addCell);
        salaryReport.getHoursWorkedByDay().entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> createCell(
                        "",// 1
                        "",// 2
                        e.getKey().toString(),//3
                        formattedToPrecision2(e.getValue())//4
                ).forEach(table::addCell));

        return table;
    }

    PdfPTable createWorkSummaryTable(MonthlySalaryReport salaryReport) throws DocumentException {
        PdfPTable table = new PdfPTable(4);
        int[] relativeWidths = IntStream.of(1, 1, 1, 1).toArray();
        table.setWidths(relativeWidths);

        createHeaderCells("Kategorien", "Anzahl", "Rate", "Betrag").forEach(table::addCell);
        createCell(
                "Stundenlohn",
                formattedToPrecision2(salaryReport.getNumberOfHoursWorked()),
                formattedToPrecision2(salaryReport.getHourlyWageExcludingHolidayExpense()),
                formattedToPrecision2(salaryReport.getWageForHoursWorked())
        ).forEach(table::addCell);
        createCell(
                "Ferienvergütung",
                "",
                getPercentageLabel(financialConstants.getHolidayExpense()),
                formattedToPrecision2(salaryReport.getAmountHolidayExpense())
        ).forEach(table::addCell);
        createCell(" ", " ", " ", " ").forEach(table::addCell);

        createHeaderCells("Bruttolohn", "", "", formattedToPrecision2(salaryReport.getGrossSalary())).forEach(table::addCell);
        createCell("AHV/IV/EO", "", getPercentageLabel(financialConstants.getAhvIvEo()), formattedToPrecision2(salaryReport.getAmountAhvIvEo())).forEach(table::addCell);
        createCell("Arbeitslosenversicherung (ALV)", "", getPercentageLabel(financialConstants.getAlv()), formattedToPrecision2(salaryReport.getAmountAlv())).forEach(table::addCell);
        createCell("Nicht-Berufsunfallversicherung (NBU)", "", getPercentageLabel(financialConstants.getNbu()), formattedToPrecision2(salaryReport.getAmountNbu())).forEach(table::addCell);
        createCell(CELL_CONTENT_ITALIC_FONT, "Total Sozialversicherungsabzüge", "", "", formattedToPrecision2(salaryReport.getTotalSocialReductions())).forEach(table::addCell);
        createCell("Quellensteuer", "", getPercentageLabel(financialConstants.getQuellensteuer()), formattedToPrecision2(salaryReport.getAmountQuellensteuer())).forEach(table::addCell);
        createCell(" ", " ", " ", " ").forEach(table::addCell);

        createHeaderCells("Abzüge", "", "", formattedToPrecision2(salaryReport.getTotalReductions())).forEach(table::addCell);
        createCell(" ", " ", " ", " ").forEach(table::addCell);

        createHeaderCells("Nettolohn", "", "", formattedToPrecision2(salaryReport.getNetSalary())).forEach(table::addCell);
        createCell(" ", " ", " ", " ").forEach(table::addCell);

        return table;
    }

    Stream<PdfPCell> createHeaderCells(String... values) {
        return Arrays.stream(values).map(this::createHeaderCell);
    }

    PdfPCell createHeaderCell(String value) {
        var phrase = new Phrase(value, CELL_HEADER_CONTENT_FONT);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setBorder(PdfPCell.BOTTOM);
        cell.setBackgroundColor(TABLE_CELL_BACKGROUND_COLOR);
        return cell;
    }

    Stream<PdfPCell> createCell(String... value) {
        return Stream.of(value).map(it -> createSingleCell(it, CELL_CONTENT_FONT));
    }

    Stream<PdfPCell> createCell(Font font, String... value) {
        return Stream.of(value).map(it -> createSingleCell(it, font));
    }

    Stream<PdfPCell> createBoldCell(String... value) {
        return Stream.of(value).map(it -> createSingleCell(it, CELL_CONTENT_BOLD__FONT));
    }

    PdfPCell createSingleCell(String value, Font font) {
        var phrase = new Phrase(value, font);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    String formattedToPrecision2(double value) {
        var df = new DecimalFormat("#.00", DecimalFormatSymbols.getInstance(locale));
        return df.format(value);
    }

}
