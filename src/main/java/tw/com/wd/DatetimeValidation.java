package tw.com.wd;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatetimeValidation {

	private static final Logger LOG = LoggerFactory.getLogger(Executor.class);
	private static final long MillisOfMinute = 60 * 1000L;
	private static final long MillisOfHour = 60 * MillisOfMinute;
	private static final long MillisOfDay = 24 * MillisOfHour;
	private static final long MillisOfWeek = 7 * MillisOfDay;
	
	private static final boolean[] daySetting = new boolean[] {true, false, true, false, true, false, false};
	private static final String StartTime = "1:30";
	private static final String EndTime = "16:30";
	
	public static void main(String[] args) {
		LOG.debug("MillisOfMinute : {}", MillisOfMinute);
		LOG.debug("MillisOfHour : {}", MillisOfHour);
		LOG.debug("MillisOfDay : {}", MillisOfDay);
		LOG.debug("MillisOfWeek : {}", MillisOfWeek);
		
		long currTime = System.currentTimeMillis();
		Date currDate = new Date(currTime);
		SimpleDateFormat.getDateTimeInstance().format(currDate).toString();
		LOG.debug("currTime : {}", currTime);
		LOG.debug("currDate : {}", currDate);
		LOG.debug("SimpleDateFormat : {}", new SimpleDateFormat("yyyy-MM-dd hh:mm:sss").format(currDate).toString());
		
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		calendar.setTimeInMillis(currTime);

		LOG.debug("TimeZone : {}", calendar.getTimeZone().toString());
		LOG.debug("getWeekYear : {}", calendar.getWeekYear());
		LOG.debug("DAY_OF_WEEK : {}", calendar.get(Calendar.DAY_OF_WEEK));
		LOG.debug("HOUR_OF_DAY : {}", calendar.get(Calendar.HOUR_OF_DAY));
		LOG.debug("MINUTE : {}", calendar.get(Calendar.MINUTE));
		
		
		if (daySetting[getDayOfWeek(calendar)]) {
			LOG.debug("Found day");
			
			long[] settingTime = getSettingTime(StartTime, EndTime, calendar);
			LOG.debug("StartTime: {}", settingTime[0]);
			LOG.debug("EndTime: {}", settingTime[1]);
			
			if (settingTime[0] <= currTime && currTime <= settingTime[1]) {
				LOG.debug("Mute");
			} else {
				LOG.debug("Not Mute");
			}
		}
	}
	
	private static int getDayOfWeek(Calendar calendar) {
		switch(calendar.get(Calendar.DAY_OF_WEEK)) {
			case Calendar.MONDAY:
				return 0;
			case Calendar.TUESDAY:
				return 1;
			case Calendar.WEDNESDAY:
				return 2;
			case Calendar.THURSDAY:
				return 3;
			case Calendar.FRIDAY:
				return 4;
			case Calendar.SATURDAY:
				return 5;
			case Calendar.SUNDAY:
				return 6;
			default:
				return -1;
		}
	}
	
	private static long[] getSettingTime(String startTime, String endTime, Calendar currCalendar) {
		long startTimestamp = getTimestamp(startTime, (Calendar)currCalendar.clone());		
		long endTimestamp = getTimestamp(endTime, (Calendar)currCalendar.clone());
		
		if (startTimestamp <= endTimestamp) {
			return new long[] {startTimestamp, endTimestamp};
		} else {
			endTimestamp = getLastTimeOfDay((Calendar)currCalendar.clone());
			return new long[] {startTimestamp, endTimestamp};
		}		
	}
	
	private static long getTimestamp(String timeString, Calendar calendar) {
		LOG.debug("=== getTimestamp ===");
		
		int hourOfDay = Integer.parseInt(timeString.split(":")[0]);
		int minute = Integer.parseInt(timeString.split(":")[1]);
		LOG.debug("hourOfDay :{}", hourOfDay);
		LOG.debug("minute :{}", minute);
		
		calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
		calendar.set(Calendar.MINUTE, minute);		
		return calendar.getTimeInMillis();
	}
	
	private static long getLastTimeOfDay(Calendar currCalendar) {
		LOG.debug("=== getLastTimeOfDay ===");
		currCalendar.set(Calendar.HOUR_OF_DAY, 23);
		currCalendar.set(Calendar.MINUTE, 59);		
		return currCalendar.getTimeInMillis();
	}
}
