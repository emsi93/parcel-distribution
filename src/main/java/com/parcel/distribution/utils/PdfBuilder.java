package com.parcel.distribution.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PdfBuilder {

    private Document document;

    public PdfBuilder(String fileName) throws FileNotFoundException, DocumentException {
        document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
    }

    public void create() throws DocumentException {
        document.open();
        setContent();
        document.close();
    }

    private void setContent() throws DocumentException {
        Font font = FontFactory.getFont("times-roman", "ISO-8859-2", 12, 1);
        Font fontBold = FontFactory.getFont("times-roman", "ISO-8859-2", 10, 1);
        Font font2 = FontFactory.getFont("times-roman", "ISO-8859-2", 10);
        Font font3 = FontFactory.getFont("times-roman", "ISO-8859-2", 7);

        Paragraph header = new Paragraph();
        header.setFont(font);
        header.setAlignment(2);
        header.add("NAGłÓWEK");
        header.add("\n");
        header.add("\n");
        header.add("\n");
        header.add("\n");
        document.add(header);

        Paragraph content = new Paragraph();
        content.setAlignment(0);
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Skąd", fontBold));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Dokąd", fontBold));
        table.addCell(cell);

        cell = new PdfPCell();
        cell.addElement(new Phrase("Adres:", font3));
        cell.addElement(new Phrase("Ostrowskiego 20 m 19\n97-200 Tomaszów Mazowiecki", font2));
        table.addCell(cell);

        cell = new PdfPCell();
        cell.addElement(new Phrase("Adres:", font3));
        cell.addElement(new Phrase("Zawadzka 133\n97-200 Tomaszów Mazowiecki", font2));
        table.addCell(cell);


        cell = new PdfPCell();
        cell.addElement(new Phrase("Nadawca:", font3));
        cell.addElement(new Phrase("Michał Krejpowicz", font2));
        cell.addElement(new Phrase("\n"));
        cell.addElement(new Phrase("E-mail:", font3));
        cell.addElement(new Phrase("krejpowiczmichal@gmail.com", font2));
        cell.addElement(new Phrase("\n"));
        cell.addElement(new Phrase("Telefon:", font3));
        cell.addElement(new Phrase("123456789", font2));
        table.addCell(cell);


        cell = new PdfPCell();
        cell.addElement(new Phrase("Nadawca:", font3));
        cell.addElement(new Phrase("Michał Krejpowicz", font2));
        cell.addElement(new Phrase("\n"));
        cell.addElement(new Phrase("E-mail:", font3));
        cell.addElement(new Phrase("krejpowiczmichal@gmail.com", font2));
        cell.addElement(new Phrase("\n"));
        cell.addElement(new Phrase("Telefon:", font3));
        cell.addElement(new Phrase("123456789", font2));
        table.addCell(cell);

        table.setHorizontalAlignment(0);
        content.add(table);
        document.add(content);

        Paragraph qrcodeParagraph = new Paragraph();
        qrcodeParagraph.setAlignment(0);
        BarcodeQRCode barcodeQRCode = new BarcodeQRCode("siema", 1200, 1200, null);
        Image codeQrImage = barcodeQRCode.getImage();
        codeQrImage.scaleAbsolute(100, 100);
        PdfPTable table2 = new PdfPTable(1);
        table2.setHorizontalAlignment(0);
        table2.setWidthPercentage(100);
        cell = new PdfPCell();
        cell.addElement(codeQrImage);
        table2.addCell(cell);
        qrcodeParagraph.add(table2);
        document.add(qrcodeParagraph);

        Paragraph parcelDescription = new Paragraph();
        parcelDescription.setAlignment(0);
        PdfPTable table3 = new PdfPTable(1);
        table3.setHorizontalAlignment(0);
        table3.setWidthPercentage(100);
        cell = new PdfPCell();
        cell.addElement(new Phrase("Opis paczki:", font3));
        table3.addCell(cell);
        parcelDescription.add(table3);
        document.add(parcelDescription);
    }
}
