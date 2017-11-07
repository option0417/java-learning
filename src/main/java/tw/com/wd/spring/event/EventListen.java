package tw.com.wd.spring.event;

import java.util.Date;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class EventListen implements ApplicationListener{

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		// TODO Auto-generated method stub
		if(event instanceof FirstEvent) {
			String name = ((FirstEvent)event).getName();
			Date time = ((FirstEvent)event).getTime();
			
			System.out.println("EventName :" + name);
			System.out.println("EventTime :" + time);
		}
		
		
	}
	
}
