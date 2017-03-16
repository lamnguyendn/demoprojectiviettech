package simple;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.exception.DRException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static net.sf.dynamicreports.report.builder.column.Columns.column;
import static net.sf.dynamicreports.report.builder.datatype.DataTypes.*;
import static net.sf.dynamicreports.report.constant.HorizontalTextAlignment.CENTER;
import static net.sf.dynamicreports.report.constant.HorizontalTextAlignment.LEFT;

public class SimpleReport {
    public static void main(String[] args) {
        Connection connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/testdb", "root", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        JasperReportBuilder report = DynamicReports.report();
        report.columns(
                column("Id", "id", integerType())
                        .setHorizontalTextAlignment(LEFT),
                column("First Name", "firstName", stringType())
                        .setHorizontalTextAlignment(LEFT),
                column("Last Name", "lastName", stringType())
                        .setHorizontalTextAlignment(LEFT),
                column("Date", "dob", dateType())
                        .setHorizontalTextAlignment(LEFT)
        ).title(Components.text("Customer data").setHorizontalTextAlignment(CENTER))
        .pageFooter(Components.pageXofY())//show page number on the page footer
        .setDataSource("SELECT * FROM customer", connection);

        try {
            //show the report
            report.show();
            //export the report to a pdf file
            report.toPdf(new FileOutputStream("E:/report.pdf"));
        } catch (DRException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
