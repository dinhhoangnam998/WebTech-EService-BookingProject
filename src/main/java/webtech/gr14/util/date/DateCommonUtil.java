package webtech.gr14.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DateCommonUtil {

	public static Date stringToDate(String format, String dateInStr) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date parsedDate = new Date();
		try {
			parsedDate = formatter.parse(dateInStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parsedDate;
	}

	public static Date getBeginDateFromDateRange(String dateRange) {
		String[] part = dateRange.split("-");
		String dateInStr = part[0];
		System.out.println("----------> part1 - datebegin : " + dateInStr);
		return DateCommonUtil.stringToDate("MM/dd/yyyy", dateInStr);
	}

	public static Date getEndDateFromDateRange(String dateRange) {
		String[] part = dateRange.split("-");
		String dateInStr = part[1];
		return DateCommonUtil.stringToDate("MM/dd/yyyy", dateInStr);
	}

	public static List<Date> getDatesBetweenBeginAndEnd(Date startDate, Date endDate) {
		List<Date> datesInRange = new ArrayList<>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(startDate);

		Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(endDate);

		while (calendar.before(endCalendar)) {
			Date result = calendar.getTime();
			datesInRange.add(result);
			calendar.add(Calendar.DATE, 1);
		}
		return datesInRange;
	}

	public static List<Date> getDatesFromStringDateRange(String dateRange) {
		Date start = getBeginDateFromDateRange(dateRange);
		Date end = getEndDateFromDateRange(dateRange);
		return getDatesBetweenBeginAndEnd(start, end);
	}
}
