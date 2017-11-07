package tw.com.wd.ws.rs.impl;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import tw.com.wd.ws.rs.IUserRestService;

@Path("/users")
public class UserRestService implements IUserRestService {
	@Override
	public Response userLogin(String userName) {
		return Response.status(200).entity("UserLogin as " + userName).build();
	}
	
	@Override
	public Response getUser() {
		return Response.status(200).entity("GetUser is called").build();
	}
	
	@Override
	public Response getUserVIP() {
		return Response.status(200).entity("GetUserVIP is called").build();
	}
}
