package op.sample;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import op.sample.hbase.hw.domain.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Executor {
	private static final Logger LOG = LoggerFactory.getLogger(Executor.class);
	private static final long MillisOfMinute = 60 * 1000L;
	private static final long MillisOfHour = 60 * MillisOfMinute;
	private static final long MillisOfDay = 24 * MillisOfHour;
	private static final long MillisOfWeek = 7 * MillisOfDay;
	
	private static final boolean[] daySetting = new boolean[] {true, false, true, false, true, false, false};
	private static final String StartTime = "1:30";
	private static final String EndTime = "16:30";
	
	public static void main(String[] args) {
        User user = new User();
        user.setUserID("fake-user_id-0001");
        user.setTimelineID("fake-timeline_id-0001");
        
        
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hbase_pu");
        EntityManager em = emf.createEntityManager();
        
        em.persist(user);
        em.close();
        emf.close();
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
