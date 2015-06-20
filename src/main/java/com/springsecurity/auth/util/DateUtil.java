package com.springsecurity.auth.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.springsecurity.nfc.constants.Constants;

public class DateUtil implements Constants{
	public static String getSQLDateFormat(Date date){
		SimpleDateFormat dateFormat = new SimpleDateFormat(MYSQL_DATE_FORMAT);
		String formattedDate=dateFormat.format(date);
		return formattedDate;
	}
	
	public static String getDayMonthDate(Date d){
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy");
		String formattedDate=dateFormat.format(d);
		return formattedDate;
	}
}
