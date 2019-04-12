package tw.com.wd.jersey.api;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Path("api/v1/hello")
@Produces("application/json;charset=utf-8")
public interface Hello {
    @GET
    @Path("/")
    Response getHello(@Context HttpHeaders headers);

    @POST
    @Path("/")
    Response sendHello(@Context HttpHeaders headers, String body);
}
