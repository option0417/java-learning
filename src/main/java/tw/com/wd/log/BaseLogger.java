package tw.com.wd.log;


import tw.com.wd.log.obj.LoggerType;

public interface BaseLogger {
    /**
     * Write log with Info level
     * @param log
     */
    public void info(String log);

    /**
     * Write log with Debug level
     * @param log
     */
    public void debug(String log);

    /**
     * Write log with Trace level
     * @param log
     */
    public void trace(String log);

    /**
     * Write log with Error level
     * @param log
     */
    public void error(String log);

    /**
     * Write exception with Error level
     * @param t
     */
    public void error(Throwable t);

    /**
     * Get tyoe of logger
     * @return
     */
    public LoggerType getLoggerType();
}
