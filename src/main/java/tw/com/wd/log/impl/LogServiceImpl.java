package tw.com.wd.log.impl;

import tw.com.wd.log.BaseLogger;
import tw.com.wd.log.LogService;
import tw.com.wd.log.LogStore;
import tw.com.wd.log.obj.LoggerType;

import java.util.HashMap;
import java.util.Map;

public class LogServiceImpl implements LogService {
    private Map<LoggerType, BaseLogger> loggerMap;
    private LogStore logStore;


    public static LogService getInstance() {
        return InstanceHolder.instance;
    }

    private static final class InstanceHolder {
        private static final LogService instance = new LogServiceImpl();
    }

    private LogServiceImpl() {
        super();
        //TODO initialLogStore();
        initialLoggerMap();
    }

    private void initialLoggerMap() {
        loggerMap = new HashMap<LoggerType, BaseLogger>();
        loggerMap.put(LoggerType.System, new SysLogger(logStore));
    }

    @Override
    public BaseLogger getLogger(LoggerType loggerType) {
        switch (loggerType) {
            case System:
            case Undefine:
            default:
                return loggerMap.get(LoggerType.System);
        }
    }
}
