package tw.com.wd.spring.exception;

import java.lang.reflect.Method;

public class LogThrowsExceptionAdviceImpl implements LogThrowsExceptionAdvice {
    public void afterThrowing(Method m, Object[] args, Object target, Exception e) {
    	System.out.println("Method : " + m.getName());    	
    	System.out.println("Exception : " + e.getClass().toString());
    	try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
}
