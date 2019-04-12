package tw.com.wd.jersey.filter.req;

import javax.ws.rs.container.ContainerRequestContext;
import java.io.IOException;

public class RandomNumReqFilter extends BaseReqFilter {
	private int num;

	public RandomNumReqFilter(int randomNum) {
		super();
		this.num = randomNum;
	}
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.printf("My Num is %d\n", this.num);
	}

}
