package op.sample.spring.event;

import java.util.Date;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

public class EventPublish implements ApplicationEventPublisherAware{
	private String name;
	private ApplicationEventPublisher applicationEventPublisher;
	
	public void setName(String _name)	{	name = _name;	}
	
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher _applicationEventPublisher) {
		// TODO Auto-generated method stub
		applicationEventPublisher = _applicationEventPublisher;
	}
	
	public void checkout() {
		FirstEvent event = new FirstEvent(this, "TEST", new Date());
		applicationEventPublisher.publishEvent(event);
	}
	
}
