package com.ecommerce.utils;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import com.ecommerce.model.Invoice;
import com.ecommerce.model.Item;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class InvoicePDFExporter {

    private final Invoice invoice;

    public InvoicePDFExporter(Invoice invoice) {
        this.invoice = invoice;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.WHITE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.COURIER);
        font.setColor(Color.BLACK);

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Seller Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Cant", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Date", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Total", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Products", font));
        table.addCell(cell);


    }

    private void writeTableData(PdfPTable table) {

        table.addCell(String.valueOf(this.invoice.getId()));
        table.addCell(String.valueOf(invoice.getSellername()));
        table.addCell(String.valueOf(this.invoice.getTotalProducts()));
        table.addCell(String.valueOf(this.invoice.getDate()));
        table.addCell(String.valueOf(this.invoice.getTotalAmount() + " $"));
        StringBuilder products = new StringBuilder();
        for (Item p : this.invoice.getProducts()) {
            products.append(p.getSellProduct().getProduct().getName()).append(", ");
        }
        table.addCell(products.toString());

    }

    public void export() throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, Files.newOutputStream(Paths.get(this.invoice.getId() + "-" + this.invoice.getDate().toString() + " Invoice.pdf")));

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER);
        font.setSize(18);
        font.setColor(Color.RED);

        Paragraph p = new Paragraph("Invoice Softtek", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1f, 2.0f, 1f, 3.0f, 1.5f, 3.5f});
        table.setSpacingBefore(10);
        writeTableHeader(table);
        writeTableData(table);
        document.add(table);
        document.close();

    }

}
