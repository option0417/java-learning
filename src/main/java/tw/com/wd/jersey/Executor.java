package tw.com.wd.jersey;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import tw.com.wd.jersey.api.HelloImpl;
import tw.com.wd.jersey.filter.req.CounterReqFilter;
import tw.com.wd.jersey.filter.req.ShowReqFilter;
import tw.com.wd.jersey.interceptor.read.AppendNumberReadInterceptor;
import tw.com.wd.jersey.interceptor.read.AppendTextReadInterceptor;
import tw.com.wd.jersey.interceptor.write.AppendTextWriteInterceptor;

import javax.ws.rs.core.Application;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Executor extends Application {
    private static final String SERVER_URI = "http://127.0.0.1:8080";


    public static void main(String[] args) throws Exception {
        startServer();
    }

    private static void startServer() throws URISyntaxException, IOException {
        Executor ap = new Executor();
        ResourceConfig conf = ResourceConfig.forApplication(ap);

        setupFilter(conf);
        setupProvider(conf);

        HttpServer httpServer = GrizzlyHttpServerFactory.createHttpServer(new URI(SERVER_URI), conf, false);



        httpServer.start();
    }

    private static void setupProvider(ResourceConfig conf) {
        conf.register(AppendTextWriteInterceptor.class);
        conf.register(AppendTextReadInterceptor.class);
        conf.register(AppendNumberReadInterceptor.class);
    }

    private static void setupFilter(ResourceConfig conf) {
    	conf.register(CounterReqFilter.class);
    	conf.register(ShowReqFilter.class);
    	conf.register(CommonDynamicBinding.class);
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> restServiceSet = new HashSet<>();
        restServiceSet.add(HelloImpl.class);
        return restServiceSet;
    }


    public Map<String, Object> getProperties() {
        Map<String, Object> p = new HashMap<>();
        p.put(ServerProperties.WADL_FEATURE_DISABLE, true);
        return p;
    }
}
