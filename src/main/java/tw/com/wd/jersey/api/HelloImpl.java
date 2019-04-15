package tw.com.wd.jersey.api;

import tw.com.wd.jersey.Random;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

public class HelloImpl implements Hello {
    private static final Logger LOG = Logger.getLogger(Hello.class.getSimpleName());
    private static final String HELLO = "{\"msg\":\"Hello, Jersery!\"}";

    @Override
    public Response getHello(HttpHeaders headers) {
        LOG.info("getHello");
        return Response.ok(HELLO).build();
    }

    @Random
    @Override
    public Response sendHello(HttpHeaders headers, String body) {
        LOG.info("sendHello");

        LOG.info("Receive: " + body);

        return Response.ok().build();
    }
}
