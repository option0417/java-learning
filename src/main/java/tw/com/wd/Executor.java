package tw.com.wd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Calendar;



public class Executor {
	private static final Logger LOG = LoggerFactory.getLogger(Executor.class);
	private static final long MillisOfMinute = 60 * 1000L;
	private static final long MillisOfHour = 60 * MillisOfMinute;
	private static final long MillisOfDay = 24 * MillisOfHour;
	private static final long MillisOfWeek = 7 * MillisOfDay;
	
	public static final boolean[] daySetting = new boolean[] {true, false, true, false, true, false, false};
	private static final String StartTime = "1:30";
	private static final String EndTime = "16:30";
	
	public static void main(String[] args) {
        final String[] texts = new String[] {"a", "b", "c", "d", "e"};


        for (int idx = 0; idx < texts.length; idx++) {
            System.out.printf(" %s ", texts[idx]);
        }
        System.out.printf("%n");

        new Executor().setArray(texts);


        for (int idx = 0; idx < texts.length; idx++) {
            System.out.printf(" %s ", texts[idx]);
        }
        System.out.printf("%n");
	}

	private void setArray(final String[] sArray) {
        String[] tmpArray = Arrays.copyOf(sArray, sArray.length);

        for (int idx = 0; idx < tmpArray.length; idx++) {
            tmpArray[idx] = new String(new byte[]{(byte)(65 + idx)});
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

