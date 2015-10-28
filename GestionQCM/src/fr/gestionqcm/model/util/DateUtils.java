package fr.gestionqcm.model.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	private final static DateFormat DateFormatter = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm:ss.S");

	private DateUtils() {

	}

	public static Date stringToDate(String string) throws ParseException {
		Date date = null;
		date = DateFormatter.parse(string);
		return date;
	}

	public static String dateToString(Date date) {
		return (date != null) ? DateFormatter.format(date) : "";
	}

	public static String getDateFromDate(Date date) {
		if (date != null) {
			Calendar calendarDate = Calendar.getInstance();
			calendarDate.setTime(date);
			return String.format("%02d-%02d-%02d",
					calendarDate.get(Calendar.YEAR),
					calendarDate.get(Calendar.MONTH),
					calendarDate.get(Calendar.DAY_OF_MONTH));
		} else {
			return "";
		}

	}

	public static String getDateHourDate(Date date) {
		if (date != null) {
			Calendar calendarDate = Calendar.getInstance();
			calendarDate.setTime(date);
			return String.format("%02d:%02d", calendarDate.get(Calendar.HOUR),
					calendarDate.get(Calendar.MINUTE));
		} else {
			return "";
		}
	}
}
