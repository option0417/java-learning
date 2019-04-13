package tw.com.wd.jersey;


import tw.com.wd.jersey.filter.req.RandomNumReqFilter;

import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;

public class CommonDynamicBinding implements DynamicFeature {

	@Override
	public void configure(ResourceInfo resourceInfo, FeatureContext context) {
		if (resourceInfo.getResourceMethod().getDeclaredAnnotation(Random.class) != null) {
			ContainerRequestFilter reqFilter = new RandomNumReqFilter((int)(Math.random() * 100 + 1));
			context.register(reqFilter);
		}

	}
}
