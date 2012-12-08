package op.sample;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


public class Executor {
	private static final Logger LOG = LogManager.getLogger(Executor.class);
	public static void main(String[] args) throws Exception {
//		new Prepopulate().prepopulate();
//		LOG.debug("** Database filled. **");
		try {
			Client client = Client.create();
	 
			WebResource webResource = client
			   .resource("http://127.0.0.1:8080/JavaLearning/rest/json/metallica/post");
			
			JSONObject jsonObj = new JSONObject();			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			jsonObj.put("recordDate", sdf.format(new Date(System.currentTimeMillis())).toString());
			jsonObj.put("itemName", "Fade To Black");
			jsonObj.put("price", "2468");
	 
			ClientResponse response = webResource.type("application/json")
			   .post(ClientResponse.class, jsonObj.toString());
	 
			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
				     + response.getStatus());
			}
	 
			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);
	 
		  } catch (Exception e) {
	 
			e.printStackTrace();
	 
		  }		
	}
}

