package com.parcel.distribution.utils;

import com.google.gson.Gson;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.parcel.distribution.db.entity.Address;
import com.parcel.distribution.db.entity.Parcel;
import com.parcel.distribution.db.repository.ParcelRepository;
import com.parcel.distribution.webapp.download.object.ParcelJsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PdfBuilder {

    private Document document;

    private Parcel parcel;


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

        String addressLine;
        Address address = parcel.getUser().getAddress();
        if(address.getFlatNumber() == null || "".equals(address.getFlatNumber()))
            addressLine = address.getStreet() + " " + address.getStreetNumber() + "\n" + address.getPostCode() + " " + address.getCity();
        else
            addressLine = address.getStreet() + " " + address.getStreetNumber() + " m " + address.getFlatNumber()+ "\n" + address.getPostCode() + " " + address.getCity();

        cell = new PdfPCell();
        cell.addElement(new Phrase("Adres:", font3));
        cell.addElement(new Phrase(addressLine, font2));
        table.addCell(cell);

        if(parcel.getRecipient().getFlatNumber() == null || "".equals(parcel.getRecipient().getFlatNumber()))
            addressLine = parcel.getRecipient().getStreet() + " " + parcel.getRecipient().getStreetNumber() + "\n" + parcel.getRecipient().getPostCode() + " " + parcel.getRecipient().getCity();
        else
            addressLine = parcel.getRecipient().getStreet() + " " + parcel.getRecipient().getStreetNumber() + " m " + parcel.getRecipient().getFlatNumber() + "\n" + parcel.getRecipient().getPostCode() + " " + parcel.getRecipient().getCity();


        cell = new PdfPCell();
        cell.addElement(new Phrase("Adres:", font3));
        cell.addElement(new Phrase(addressLine, font2));
        table.addCell(cell);


        cell = new PdfPCell();
        cell.addElement(new Phrase("Nadawca:", font3));
        cell.addElement(new Phrase(parcel.getUser().getName() + " " + parcel.getUser().getSurname(), font2));
        cell.addElement(new Phrase("\n"));
        cell.addElement(new Phrase("E-mail:", font3));
        cell.addElement(new Phrase(parcel.getUser().getEmail(), font2));
        cell.addElement(new Phrase("\n"));
        cell.addElement(new Phrase("Telefon:", font3));
        cell.addElement(new Phrase(parcel.getUser().getPhoneNumber(), font2));
        table.addCell(cell);


        cell = new PdfPCell();
        cell.addElement(new Phrase("Odbiorca:", font3));
        cell.addElement(new Phrase(parcel.getRecipient().getName() + " " + parcel.getRecipient().getSurname(), font2));
        cell.addElement(new Phrase("\n"));
        cell.addElement(new Phrase("E-mail:", font3));
        cell.addElement(new Phrase(parcel.getRecipient().getEmail(), font2));
        cell.addElement(new Phrase("\n"));
        cell.addElement(new Phrase("Telefon:", font3));
        cell.addElement(new Phrase(parcel.getRecipient().getPhoneNumber(), font2));
        table.addCell(cell);

        table.setHorizontalAlignment(0);
        content.add(table);
        document.add(content);

        Paragraph qrcodeParagraph = new Paragraph();
        qrcodeParagraph.setAlignment(0);
        Gson gson = new Gson();
        ParcelJsonObject parcelJsonObject = ParcelJsonObject.builder()
                .id(parcel.getId())
                .code(parcel.getCode())
                .build();
        String json = gson.toJson(parcelJsonObject);
        BarcodeQRCode barcodeQRCode = new BarcodeQRCode(json, 1200, 1200, null);
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
        cell.addElement(new Phrase(parcel.getDescription(), font2));
        table3.addCell(cell);
        parcelDescription.add(table3);
        document.add(parcelDescription);
    }

    public void setParcel(Parcel parcel) {
        this.parcel = parcel;
    }
}
