package tw.com.wd.jersey.interceptor.read;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Priority(Priorities.ENTITY_CODER + 1)
public class AppendTextReadInterceptor implements ReaderInterceptor {
    @Override
    public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
        System.out.printf("AppendTextReadInterceptor aroundReadFrom\n");

        InputStream ins = context.getInputStream();

        byte[] dataBytes = new byte[ins.available()];
        ins.read(dataBytes);

        String req = new String(dataBytes);
        req += "isRead: true";

        context.setInputStream(new ByteArrayInputStream(req.getBytes()));
        return context.proceed();
    }
}
