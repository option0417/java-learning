package tw.com.wd.jersey.filter.req;

import javax.ws.rs.container.ContainerRequestContext;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class ShowReqFilter extends BaseReqFilter {

	@Override
	public void filter(ContainerRequestContext reqContext) throws IOException {
		
		
		
		System.out.printf("ReqMethod: %s\n", reqContext.getRequest().getMethod());
		
		Iterator<Entry<String, List<String>>> iter = reqContext.getHeaders().entrySet().iterator();
		
		while (iter.hasNext()) {
			Entry<String, List<String>> entry = iter.next();

			System.out.printf("ReqHeader: %s\n", entry.getKey());
			
			for (String s : entry.getValue()) {
				System.out.printf("\tvalue: %s\n", s);
			}
		}
		
		
		InputStream ins = reqContext.getEntityStream();
		int length = ins.available();
		
		byte[] reqEntity = new byte[length];	
		ins.read(reqEntity);

		System.out.printf("ReqEntity: %s\n", new String(reqEntity));
		
		reqContext.setEntityStream(new ByteArrayInputStream(reqEntity));
	}
}
