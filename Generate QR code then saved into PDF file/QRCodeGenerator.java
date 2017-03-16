package com.aa;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import javax.imageio.ImageIO;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

/**
 * Created by vhphat on 11/19/2016.
 */
public class QRCodeGenerator {
    private static Font bigFont = new Font(Font.FontFamily.TIMES_ROMAN, 30  , Font.BOLD);
    //private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);

    public static void main(String[] args) {
        String outputPDFFileLocation = "F:\\QR_Output_file.pdf";
        String qrCodeText = "JV11";
        int size = 250;

        generatePDF(outputPDFFileLocation, qrCodeText, size);
    }


    public static boolean generatePDF(String pdfFileLocation, String qrCodeText, int size) {
        try {
            // prepare QR code image
            Map<EncodeHintType, Object> hintMap = new EnumMap<>(EncodeHintType.class);
            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size,
                    size, hintMap);
            int width = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(width, width, BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, width);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            // convert BufferedImage -> byte[]
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write( image, "png", baos );
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();

            // use iText to create PDF file, this file contains the above QR code image
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(pdfFileLocation));
            document.open();

            Image qrCodeImg = Image.getInstance(imageInByte);
            qrCodeImg.setAbsolutePosition(180f, 520f);
            qrCodeImg.scalePercent(100f);//100%
            document.add(qrCodeImg);

            // add metadata of the PDF file
            document.addTitle("Online Ticket");
            document.addSubject("Online Ticket");
            document.addKeywords("Ticket, Online");
            document.addAuthor("Admin");
            document.addCreator("Admin");

            Paragraph title = new Paragraph();

            addEmptyLine(new Paragraph(), 3);
            Paragraph paragraph = new Paragraph("Online Ticket: X8AH9J", bigFont);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            title.add(paragraph);
            document.add(title);

            document.close();

        } catch (WriterException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return true;
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
