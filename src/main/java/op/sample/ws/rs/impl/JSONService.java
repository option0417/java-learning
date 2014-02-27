package op.sample.ws.rs.impl;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import op.sample.spring.domain.BitOrganizer;
import op.sample.spring.service.IBitOrganizerService;
import op.sample.ws.rs.IJSONService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Path("/json/metallica")
public class JSONService implements IJSONService {
	@Autowired
	private IBitOrganizerService bitOrganizerService;
	
	@Override
	public BitOrganizer getBitOrganizerInJSON() {		 
		return bitOrganizerService.find(BitOrganizer.class, Long.valueOf(0));
	}

	@Override
	public Response createBitOrganizerInJSON(BitOrganizer bitOrganizer) {
		String result = "BitOrganizer saved : \n" + bitOrganizer;
		return Response.status(201).entity(result).build();
	}

}
