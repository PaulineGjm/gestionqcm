package fr.gestionqcm.model.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	private final static DateFormat DateFormatter = new SimpleDateFormat(
			"yyyy-MM-dd");

	private DateUtils() {

	}

	public static Date stringToDate(String string) throws ParseException {
		Date date = null;
		if (string != null && string.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
			date = DateFormatter.parse(string);
		}
		return date;
	}

	public static String dateToString(Date date) {
		return (date != null) ? DateFormatter.format(date) : "";
	}

	public static String getDateFromDate(Date date) {
		Calendar calendarDate = Calendar.getInstance();
		calendarDate.setTime(date);
		return String.format("%02d-%02d-%02d", calendarDate.get(Calendar.YEAR),
				calendarDate.get(Calendar.MONTH),
				calendarDate.get(Calendar.DAY_OF_MONTH));
	}

	public static String getDateHourDate(Date date) {
		Calendar calendarDate = Calendar.getInstance();
		calendarDate.setTime(date);
		return String.format("%02d:%02d", calendarDate.get(Calendar.HOUR),
				calendarDate.get(Calendar.MINUTE));
	}
}
