package com.springsecurity.auth.util;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.springsecurity.nfc.constants.Constants;
import com.springsecurity.nfc.model.Orders;

/**
 * @author Gaurav Oli
 * @date 29 April, 2015
 *
 */
public abstract class BillGenerator implements Constants{
	
	private final static SimpleDateFormat dateFormat = new SimpleDateFormat(MYSQL_DATE_FORMAT);
	
	public static void generateBill(List<Orders> orderList, HttpServletResponse response) throws IOException {
		Document document = new Document();
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=bill.pdf");
		OutputStream stream = response.getOutputStream();
        try {
        	PdfWriter.getInstance(document, stream);
        	document.open();
    		document.setPageSize(PageSize.A4);
    		document.add(createHeader());
        	document.add(createTable(orderList));
            document.close();
            stream.flush();
            stream.close();
        } catch(Exception e){
        	System.out.println(e.getMessage());
        }
	}
	
	public static PdfPTable createHeader() throws DocumentException {
		PdfPTable header = new PdfPTable(3);
		header.setWidthPercentage(100);
		header.setWidths(new float[]{2.0f, 2.5f, 1.5f});
		PdfPCell date = new PdfPCell(new Phrase(dateFormat.format(new Date())));
		date.setBorder(0);
		PdfPCell empty = new PdfPCell(new Phrase());
		empty.setBorder(0);
		PdfPCell companyLogo = new PdfPCell(new Phrase("Company Name"));
		companyLogo.setBorder(0);
		header.addCell(date);
		header.addCell(empty);
		header.addCell(companyLogo);
		
		header.setSpacingAfter(50);
		return header;
	}
	
	public static PdfPTable createTable(List<Orders> orderList) throws DocumentException {
        double totalAmount = 0f;
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{3.5f, 1.0f, 1.5f});
 
        Font font = new Font(FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.WHITE);
        PdfPCell cell = new PdfPCell(new Phrase("Bill Details",font));

        cell.setColspan(6);
        Style.headerCellStyle(cell);
        table.addCell(cell);
 
        table.addCell(createLabelCell(String.valueOf("NAME")));
    	table.addCell(createLabelCell(String.valueOf("QUANTITY")));
    	table.addCell(createValueCell(String.valueOf("AMOUNT")));
    	
        for (Orders order : orderList) {
        	table.addCell(createLabelCell(String.valueOf(order.getMenu_id())));
        	table.addCell(createLabelCell(String.valueOf(order.getQuantity())));
        	table.addCell(createValueCell(String.valueOf(order.getAmount())));
        	totalAmount+=order.getAmount();
        }
        PdfPCell cell1 = new PdfPCell(new Phrase());
        cell1.setBorder(0);
        PdfPCell cell2 = new PdfPCell(new Phrase("Total Amount"));
        PdfPCell cell3 = createLabelCell(String.valueOf(totalAmount));
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
    	cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
        
    	table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        return table;
    }
 
    private static PdfPCell createLabelCell(String text){
        Font font = new Font(FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.DARK_GRAY);
        PdfPCell cell = new PdfPCell(new Phrase(text,font));
        Style.labelCellStyle(cell);
        return cell;
    }
 
    private static PdfPCell createValueCell(String text){
        Font font = new Font(FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text,font));
        Style.valueCellStyle(cell);
        return cell;
    }
	
}
