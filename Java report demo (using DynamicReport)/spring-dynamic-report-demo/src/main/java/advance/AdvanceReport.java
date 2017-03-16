package advance;

import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.style.FontBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

/**
 * Created by vhphat on 11/29/2016.
 */
public class AdvanceReport {
    private static void build() {
        FontBuilder boldFont = stl.fontArialBold().setFontSize(12);

        TextColumnBuilder<String> seriesColumn = col.column("Series", "series", type.stringType());
        TextColumnBuilder<Date> dateColumn = col.column("Date", "date", type.dateType());
        TextColumnBuilder<Double> highColumn = col.column("High", "high", type.doubleType());
        TextColumnBuilder<Double> lowColumn = col.column("Low", "low", type.doubleType());
        TextColumnBuilder<Double> openColumn = col.column("Open", "open", type.doubleType());
        TextColumnBuilder<Double> closeColumn = col.column("Close", "close", type.doubleType());
        TextColumnBuilder<Double> volumeColumn = col.column("Volume", "volume", type.doubleType());

        try {
            report().setTemplate(Templates.reportTemplate)
                    .columns(seriesColumn, dateColumn, highColumn, lowColumn, openColumn, closeColumn, volumeColumn)
                    .title(Templates.createTitleComponent("CandlestickChart"))
                    .summary(
                        cht.candlestickChart()
                            .setTitle("Candlestick chart")
                            .setTitleFont(boldFont)
                            .setSeries(seriesColumn)
                            .setDate(dateColumn)
                            .setHigh(highColumn)
                            .setLow(lowColumn)
                            .setOpen(openColumn)
                            .setClose(closeColumn)
                            .setVolume(volumeColumn)
                            .setTimeAxisFormat(
                                cht.axisFormat().setLabel("Date"))
                                .setValueAxisFormat(
                                    cht.axisFormat().setLabel("Value"))
                    ).pageFooter(Templates.footerComponent)
                    .setDataSource(createDataSource())
                    .show().toPdf(new FileOutputStream("E:/report2.pdf"));
        } catch (DRException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private static JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("series", "date", "high", "low", "open", "close", "volume");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -20);
        for (int i = 0; i < 20; i++) {
            dataSource.add("serie", c.getTime(), 150 + Math.random() * 50, 20 + Math.random() * 30, 50 + Math.random() * 90, 50 + Math.random() * 110, 50 + Math.random() * 100);
            c.add(Calendar.DAY_OF_MONTH, 1);
        }

        return dataSource;
    }

    public static void main(String[] args) {
        build();
    }
}
