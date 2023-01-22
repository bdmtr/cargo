package com.cargo.controller.command;

import com.cargo.controller.Path;
import com.cargo.model.entity.Cargo;
import com.cargo.model.service.BranchService;
import com.cargo.model.service.CargoService;
import com.cargo.model.service.UserService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateInvoiceCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(CreateInvoiceCommand.class);
    CargoService cargoService;
    UserService userService;
    BranchService branchService;

    public CreateInvoiceCommand(CargoService cargoService, UserService userService, BranchService branchService) {
        this.cargoService = cargoService;
        this.userService = userService;
        this.branchService = branchService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int cargoID = Integer.parseInt(request.getParameter("invoice_id"));
        Cargo cargo = cargoService.getCargoById(cargoID);
        int senderId = cargo.getUserId();
        String sender = userService.findUserById(senderId).getFullname();
        String type = cargo.getType();
        String cityDeparture = String.valueOf(branchService.getBranchById(cargo.getDepartureBranchId()).getCity());
        String cityDestination = String.valueOf(branchService.getBranchById(cargo.getDestinationBranchId()).getCity());
        Timestamp ts = cargo.getCreationDate();
        Date date = new Date();
        date.setTime(ts.getTime());
        String creationDateF = new SimpleDateFormat("MM.dd.yyyy").format(date);

        ts = cargo.getDeliveryDate();
        date.setTime(ts.getTime());
        String deliveryDateF = new SimpleDateFormat("MM.dd.yyyy").format(date);

        int price = cargo.getPrice();

        try {
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setContentType("application/pdf");
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            PdfPTable orderInfoTable = new PdfPTable(3);
            orderInfoTable.setWidthPercentage(100);
            orderInfoTable.addCell(getIRHCell(""));
            orderInfoTable.addCell(getIRHCell(""));
            orderInfoTable.addCell(getIRHCell("Invoice"));
            orderInfoTable.addCell(getIRHCell(""));
            orderInfoTable.addCell(getIRHCell(""));

            Font baseFont = FontFactory.getFont("dejavusans.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            String invoiceTo = "Invoice to " + sender;
            Phrase invoice = new Phrase(invoiceTo, baseFont);
            PdfPTable invoiceTable = new PdfPTable(6);
            invoiceTable.setWidthPercentage(100);
            invoiceTable.setWidths(new float[]{2, 3, 4, 3, 3, 2});
            invoiceTable.setSpacingBefore(30.0f);
            invoiceTable.addCell(getHeaderCell("Order #"));
            invoiceTable.addCell(getHeaderCell("Cargo type"));
            invoiceTable.addCell(getHeaderCell("Direction"));
            invoiceTable.addCell(getHeaderCell("Creation date"));
            invoiceTable.addCell(getHeaderCell("Delivery date"));
            invoiceTable.addCell(getHeaderCell("Price"));

            invoiceTable.addCell(getRowCell(String.valueOf(cargoID)));
            invoiceTable.addCell(getRowCell(type));
            invoiceTable.addCell(getRowCell("From " + cityDeparture + " to " + cityDestination));
            invoiceTable.addCell(getRowCell(creationDateF));
            invoiceTable.addCell(getRowCell(deliveryDateF));
            invoiceTable.addCell(getRowCell(String.valueOf(price)));

            PdfPTable validity = new PdfPTable(1);
            validity.setWidthPercentage(100);
            validity.addCell(getValidityCell());


            PdfPCell summaryL = new PdfPCell(validity);
            summaryL.setColspan(3);
            summaryL.setPadding(1.0f);
            invoiceTable.addCell(summaryL);

            PdfPTable accounts = new PdfPTable(2);
            accounts.setWidthPercentage(100);

            accounts.addCell(getAccountsCell());
            accounts.addCell(getAccountsCellR(String.valueOf(price)));

            PdfPCell summaryR = new PdfPCell(accounts);
            summaryR.setColspan(3);
            invoiceTable.addCell(summaryR);

            document.add(invoice);
            document.add(invoiceTable);
            document.close();

            return Path.PAGE_MANAGER;
        } catch (Exception e) {
            LOGGER.error("Cant build PDF for cargo " + cargo.getId());

        }
        return Path.PAGE_MANAGER;
    }

    private PdfPCell getIRHCell(String text) {
        FontSelector fs = new FontSelector();
        Font baseFont = FontFactory.getFont("dejavusans.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 16);
        fs.addFont(baseFont);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setPadding(5);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    private PdfPCell getHeaderCell(String text) {
        FontSelector fs = new FontSelector();
        Font baseFont = FontFactory.getFont("dejavusans.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 12);
        baseFont.setColor(BaseColor.GRAY);
        fs.addFont(baseFont);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5.0f);
        return cell;
    }

    private PdfPCell getRowCell(String text) {
        Font baseFont = FontFactory.getFont("dejavusans.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 12);
        Paragraph paragraph = new Paragraph(text, baseFont);
        PdfPCell cell = new PdfPCell(paragraph);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5.0f);
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthTop(0);
        return cell;
    }

    private PdfPCell getValidityCell() {
        FontSelector fs = new FontSelector();
        Font baseFont = FontFactory.getFont("dejavusans.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10);
        baseFont.setColor(BaseColor.GRAY);
        fs.addFont(baseFont);
        Phrase phrase = fs.process("Beneficiary's account number: 11230001001");
        PdfPCell cell = new PdfPCell(phrase);
        cell.setBorder(0);
        return cell;
    }

    private PdfPCell getAccountsCell() {
        FontSelector fs = new FontSelector();
        Font baseFont = FontFactory.getFont("dejavusans.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10);
        fs.addFont(baseFont);
        Phrase phrase = fs.process("Total");
        PdfPCell cell = new PdfPCell(phrase);
        cell.setBorderWidthRight(0);
        cell.setBorderWidthTop(0);
        cell.setPadding(5.0f);
        return cell;
    }

    private PdfPCell getAccountsCellR(String text) {
        FontSelector fs = new FontSelector();
        Font baseFont = FontFactory.getFont("dejavusans.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10);
        fs.addFont(baseFont);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setBorderWidthLeft(0);
        cell.setBorderWidthTop(0);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setPadding(5.0f);
        cell.setPaddingRight(20.0f);
        return cell;
    }
}
