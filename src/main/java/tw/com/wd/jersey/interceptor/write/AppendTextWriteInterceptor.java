package tw.com.wd.jersey.interceptor.write;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;
import java.io.IOException;

public class AppendTextWriteInterceptor implements WriterInterceptor {
    @Override
    public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
        System.out.printf("aroundWriteTo\n");
        String resp = (String) context.getEntity();
        resp += "isWrite: true";

        context.setEntity(resp);
        context.proceed();
    }
}
