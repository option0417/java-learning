package op.sample.ws.rs;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

public class JSONServiceClient {
	private static final String URL_PREFIX = "http://";
	private static final String URL_DEF_HOST = "localhost:8080";
	private static final String URL_DEF_TAGET = "/JavaLearning/rest/json/metallica/post";
	private static final Logger LOG = LogManager.getLogger(JSONServiceClient.class);	
	private URI uri;
	private JSONObject jsonObj;

	public JSONServiceClient() {
		super();
	}
	
	public JSONServiceClient(URI uri, JSONObject jsonObj) {
		setURI(uri);
		setJSONObject(jsonObj);
	}
	
	public void setURI(URI uri) {
		this.uri = uri;
	}
	
	public void setJSONObject(JSONObject jsonObj) {
		this.jsonObj = jsonObj;
	}
	
	public void sendRequest() {
		try {
			ClientResponse response = getClientResponse();
			
			if (checkClientResponse(response)) {
				LOG.info("Output from Server .... \n");
				LOG.info(response.getEntity(String.class));
			}	 
		  } catch (Exception e) {
			  LOG.error(e.getMessage());
			  e.printStackTrace(System.out); 
		  }
	}
	
	private ClientResponse getClientResponse()
			throws UniformInterfaceException,
			ClientHandlerException,
			URISyntaxException,
			JSONException {
		return createWebResource()
				.type("application/json")
				.post(ClientResponse.class, getJSONObject().toString());
	}
	
	private WebResource createWebResource() throws URISyntaxException {
		if (uri == null) {
			uri = getDefaultURI();
		}
		return Client.create().resource(uri);
	}
	
	private URI getDefaultURI() throws URISyntaxException {
		return new URI(URL_PREFIX + URL_DEF_HOST + URL_DEF_TAGET);
	}
	
	private JSONObject getJSONObject() throws JSONException {
		if (jsonObj == null) {
			return getDefJSONObject();
		} else {
			return jsonObj;
		}
	}
	
	private JSONObject getDefJSONObject() throws JSONException {
		JSONObject jsonObj = new JSONObject();			
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		jsonObj.put("recordDate", sdf.format(new Date(System.currentTimeMillis())).toString());
		jsonObj.put("itemName", "Fade To Black");
		jsonObj.put("price", "2468");
		return jsonObj;
	}
	
	private boolean checkClientResponse(ClientResponse clientResponse) {
		if (clientResponse.getStatus() != 201) {
			LOG.error("Failed : HTTP error code : " + clientResponse.getStatus());
			return false;
		} else {
			return true;
		}
	}
}
