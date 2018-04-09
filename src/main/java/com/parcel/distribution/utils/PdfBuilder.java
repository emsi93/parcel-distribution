package com.parcel.distribution.utils;

import com.itextpdf.text.*;
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
        PdfPCell cell;
        PdfPTable table = new PdfPTable(2);
        Font font = FontFactory.getFont("times-roman", "ISO-8859-2",8);

        Rectangle rect = new Rectangle(523, 770);
        rect.getLeft(10);
        table.setWidthPercentage(new float[]{ 72, 72 }, rect);

        cell = new PdfPCell(new Phrase("Imie i nazwisko:", font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Michał Krejpowicz", font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("numer albumu", font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("214446", font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Kierunek:", font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Informatyka:", font));
        table.addCell(cell);
        document.add(table);
    }
}
