package tw.com.wd.log.impl;

import tw.com.wd.log.BaseLogger;
import tw.com.wd.log.LogStore;
import tw.com.wd.log.obj.LogLevel;
import tw.com.wd.log.obj.LoggerType;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SysLogger implements BaseLogger {
    // Format: yyyy-mm-dd hh:mm:ss [LoggerType][Level] content
    private static String LOG_FORMAT            = "%s [%s][%s] %s";
    private static final SimpleDateFormat SDF   = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private LogStore logStore;

    public SysLogger(LogStore logStore) {
        super();
        this.logStore   = logStore;
    }

    @Override
    public void info(String log) {
        logStore.writeLog(format(log, LogLevel.Info));
    }

    @Override
    public void debug(String log) {
        logStore.writeLog(format(log, LogLevel.Debug));
    }

    @Override
    public void trace(String log) {
        logStore.writeLog(format(log, LogLevel.Trace));
    }

    @Override
    public void error(String log) {
        logStore.writeLog(format(log, LogLevel.Error));
    }

    @Override
    public void error(Throwable t) {
        StackTraceElement[] stackTraceElements = t.getStackTrace();
        StringBuilder logBuilder = new StringBuilder((stackTraceElements.length | 1) * 100);

        logBuilder.append(t.getClass().getSimpleName());
        logBuilder.append(": ");
        logBuilder.append(t.getMessage());
        logBuilder.append("\n");
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            logBuilder.append("\tat\t");
            logBuilder.append(stackTraceElement.toString());
            logBuilder.append("\n");
        }

        logStore.writeLog(format(logBuilder.toString(), LogLevel.Error));
    }

    @Override
    public LoggerType getLoggerType() {
        return LoggerType.System;
    }

    private String format(String log, LogLevel logLevel) {
        return String.format(LOG_FORMAT, SDF.format(new Date()), LoggerType.System.name(), logLevel.name(), log);
    }
}
