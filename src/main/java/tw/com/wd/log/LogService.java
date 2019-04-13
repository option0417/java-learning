package tw.com.wd.log;

import tw.com.wd.log.obj.LoggerType;

public interface LogService {
    /**
     * Get system-level logger
     * @return
     */
    public BaseLogger getLogger(LoggerType loggerType);
}
