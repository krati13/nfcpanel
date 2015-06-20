package com.springsecurity.nfc.download;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.springsecurity.nfc.constants.Constants;
import com.springsecurity.nfc.model.Orders;
import com.springsecurity.nfc.model.TxnHistory;

@Component
public class XlsxDownloader implements Constants {
	
	public void downloadTxnHistory(List<TxnHistory> txnList, HttpServletResponse response) {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(); 
			XSSFSheet sheet = workbook.createSheet("Transaction");
			
	        createHeader(TXN_HEADER.split(COMMA), sheet, workbook);
	        
	        int index = ONE;
	        for(TxnHistory txnHistory : txnList) {
	        	Row row = sheet.createRow(index++);
	        	row.createCell(0).setCellValue(txnHistory.getTxn_id());
	        	row.createCell(1).setCellValue(txnHistory.getOrder_id());
	        	row.createCell(2).setCellValue(txnHistory.getTax_amt());
	        	row.createCell(3).setCellValue(txnHistory.getShipping_amt());
	        	row.createCell(4).setCellValue(txnHistory.getOrder_amt());
	        	row.createCell(5).setCellValue(txnHistory.getTotal_amt());
	        	row.createCell(6).setCellValue(txnHistory.getBank_txn_id());
	        	row.createCell(7).setCellValue(txnHistory.getStatus());
	        	row.createCell(8).setCellValue(txnHistory.getCreated_on());
	        }
	       
	        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	        response.setHeader("Content-Disposition", "inline; filename=transaction.xlsx");
	        OutputStream outputStream = response.getOutputStream();
			workbook.write(outputStream);
		    outputStream.flush();
		    outputStream.close();
		    workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void downloadOrders(List<Orders> orderList, HttpServletResponse response) {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(); 
			XSSFSheet sheet = workbook.createSheet("Order");
			
	        createHeader(ORDER_HEADER.split(COMMA), sheet, workbook);
	        
	        int index = ONE;
	        for(Orders orders : orderList) {
	        	Row row = sheet.createRow(index++);
	        	row.createCell(0).setCellValue(orders.getOrder_id());
	        	row.createCell(1).setCellValue(orders.getTable_id());
	        	row.createCell(2).setCellValue(orders.getMenu_id());
	        	row.createCell(3).setCellValue(orders.getQuantity());
	        	row.createCell(4).setCellValue(orders.getAmount());
	        	row.createCell(5).setCellValue(orders.getStatus());
	        	row.createCell(6).setCellValue(orders.getModified_on());
	        	row.createCell(7).setCellValue(orders.getCreated_on());
	        }
	       
	        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	        response.setHeader("Content-Disposition", "inline; filename=order.xlsx");
	        OutputStream outputStream = response.getOutputStream();
			workbook.write(outputStream);
		    outputStream.flush();
		    outputStream.close();
		    workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void createHeader(String[] data, XSSFSheet sheet, XSSFWorkbook workbook) {
		// create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
        
		Row header = sheet.createRow(0);
		int index = 0;
		for (String headerTitle : data) {
			header.createCell(index).setCellValue(headerTitle);
			header.getCell(index++).setCellStyle(style);
		}
	}	
}