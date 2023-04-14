package com.growee.database.redis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;


/**
 * @author Maor
 *
 */
public class TTLCalculator {

	private static final int TWENTI_FOUR_HOURS_IN_SEC = 86400; // 24 hours
	// 12 months in seconds
	private static final int MONTH = 12;
	private static final int TIME_TO_EXPIRE_IN_SECONDS = 31 * MONTH * TWENTI_FOUR_HOURS_IN_SEC;

	// calculate time to expire 12 months for the end of the day 0:00
	public static int getTimeToExpiredInSeconds() {
		int current = getCurrentTimeInSeconds() % TWENTI_FOUR_HOURS_IN_SEC;
		int secondsToAdd = TWENTI_FOUR_HOURS_IN_SEC - current;
		int finalTimeForExpire = secondsToAdd + TIME_TO_EXPIRE_IN_SECONDS; 
		return finalTimeForExpire;
	}
	
	public static String getCurrentDate() {
		LocalDate localDate = LocalDate.now();
		String date= DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate);
		return date;
	}
	
	public static String getCurrentDateMinusDays(int numberDaysToSubtract) {
		LocalDate localDate = LocalDate.now();
		LocalDate date =  localDate.minusDays(numberDaysToSubtract);
		return DateTimeFormatter.ofPattern("yyy/MM/dd").format(date);
	}
	
	public static String getCurrentDatePlusDays(int numberDaysToAdd) {
		LocalDate localDate = LocalDate.now();
		LocalDate date =  localDate.plusDays(numberDaysToAdd);
		return DateTimeFormatter.ofPattern("yyy/MM/dd").format(date);
	}

	public static String getThisDatePlusDays(String thisDate, int numberDaysToAdd) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(thisDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.DATE, numberDaysToAdd);  // number of days to add
		String res = sdf.format(c.getTime());  // dt is now the new date
		return res;
	}


	public static int getCurrentTimeInSeconds() {
		return milisecToSeconds((new Date()).getTime());
	}

	private static int milisecToSeconds(long milisec) {
		return (int) (milisec / 1000);
	}

}
