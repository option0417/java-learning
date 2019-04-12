package tw.com.wd.jersey.filter.req;

import javax.ws.rs.container.ContainerRequestContext;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class CounterReqFilter extends BaseReqFilter {
	private static final AtomicInteger COUNT = new AtomicInteger(0);

	@Override
	public void filter(ContainerRequestContext reqContext) throws IOException {
		int cnt = COUNT.incrementAndGet();
		System.out.printf("Req count: %d\n", cnt);
		return;
	}
}
