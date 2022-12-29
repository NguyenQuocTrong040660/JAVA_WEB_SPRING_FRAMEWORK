package com.training.fa.uitils;

import java.sql.Date;
import java.text.ParseException;

public class DateUtils {

	public static Date convertStringToDate(String dateString) throws ParseException {
		Date date = Date.valueOf(dateString);
		return date;
	}

	public static Date convertJavaDateToSqlDate(Date javaDate) {
		Date sqlDate = new Date(javaDate.getTime());
		return sqlDate;
	}

}
