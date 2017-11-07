package tw.com.wd.spring.event;

import java.util.Date;

import org.springframework.context.ApplicationEvent;

public class FirstEvent extends ApplicationEvent{
	private String name;
	private Date time;
	

	public FirstEvent(Object source, String _name, Date _time) {
		super(source);
		name = _name;
		time = _time;
	}
	
	public String getName()	{	return name;	}
	public Date getTime()	{	return time;	}
}
