package org.example.timelog.reporting.generator;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@ApplicationScoped
public class MonthlyBalanceGenerator {

    private static final Logger LOGGER = Logger.getLogger(MonthlyBalanceGenerator.class);
    private final Locale locale = new Locale.Builder().setLanguage("de").setRegion("CH").build();

    public InputStream generateMonthlyReport(int year, int month) {
        try {
            Document document = new Document(PageSize.A4, 20, 20, 20, 20);

            Chunk chunk = new Chunk("Lohnabrechnung: " + Month.of(month).getDisplayName(TextStyle.FULL, locale),
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
            Paragraph paragraph = new Paragraph(chunk);
            paragraph.add(Chunk.NEWLINE);
            paragraph.add(Chunk.NEWLINE);
            paragraph.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(4);
            int[] relativeWidths = IntStream.of(1, 1, 1, 1).toArray();
            table.setWidths(relativeWidths);
            table.setHeaderRows(1);

            /*
             * Categories: Header
             */
            PdfPCell categoryCell = new PdfPCell(new Phrase("Category"));
            categoryCell.setBackgroundColor(new BaseColor(0, 173, 239));
            table.addCell(categoryCell);

            PdfPCell countOfCell = new PdfPCell(new Phrase("Count"));
            countOfCell.setBackgroundColor(new BaseColor(0, 173, 239));
            table.addCell(countOfCell);

            PdfPCell rateCell = new PdfPCell(new Phrase("Rate"));
            rateCell.setBackgroundColor(new BaseColor(0, 173, 239));
            table.addCell(rateCell);

            PdfPCell amountCell = new PdfPCell(new Phrase("Amount"));
            amountCell.setBackgroundColor(new BaseColor(0, 173, 239));
            table.addCell(amountCell);

            /*
             * Categories: Data
             */
            createCellWithPhrase("Stundenlohn", "100.51", "21.20", "2133.69").forEach(table::addCell);
            createCellWithPhrase("Ferienvergütung", "", "8.333%", "177.80").forEach(table::addCell);

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, os);
            document.open();
            document.add(paragraph);
            document.add(table);
            document.close();

            return new ByteArrayInputStream(os.toByteArray());
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException("Generation failed", e);
        }
    }

    private Stream<PdfPCell> createCellWithPhrase(String... value) {
        return Stream.of(value).map(this::createSingleCellWithPhrase);
    }

    private PdfPCell createSingleCellWithPhrase(String value) {
        var phrase = new Phrase(value);
        var cell = new PdfPCell();
        cell.addElement(phrase);
        return cell;
    }
}
