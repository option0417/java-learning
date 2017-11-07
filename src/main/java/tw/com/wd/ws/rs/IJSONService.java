package tw.com.wd.ws.rs;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tw.com.wd.spring.domain.BitOrganizer;

public interface IJSONService {
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public BitOrganizer getBitOrganizerInJSON();
 
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createBitOrganizerInJSON(BitOrganizer bitOrganizer);
}
