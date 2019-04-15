package tw.com.wd.scheduler.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScheduleLogger {
    private static final Logger LOG = LoggerFactory.getLogger("Scheduler");

    public static final Logger getLogger() {
        return LOG;
    }
}
