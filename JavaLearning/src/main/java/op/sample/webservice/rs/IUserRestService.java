package op.sample.webservice.rs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public interface IUserRestService {
	@GET
	@Path("/login/{userName}")
	public Response userLogin(@PathParam("userName") String userName);
	
	@GET
	public Response getUser();
	
	@GET
	@Path("/vip")
	public Response getUserVIP();
}
