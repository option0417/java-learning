package tw.com.wd.scheduler.obj;

public class ScheduleException extends RuntimeException {
    /**
     * Default constructor
     */
    public ScheduleException() {
        super();
    }

    /**
     * Constructor with message for exception
     * @param message
     */
    public ScheduleException(String message) {
        super(message);
    }

    /**
     * Constructor with throwable for exception
     * @param cause
     */
    public ScheduleException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor with message and throwable for exception
     * @param message
     * @param cause
     */
    public ScheduleException(String message, Throwable cause) {
        super(message, cause);
    }
}
