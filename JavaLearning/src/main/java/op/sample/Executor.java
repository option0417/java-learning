package op.sample;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import op.sample.random.RandomGenerator;
import op.sample.random.RandomThread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;


public class Executor extends DefaultHandler {
	private static final Logger LOG = LogManager.getLogger(Executor.class);
	private static final List<String> xsdURIList = new ArrayList<String>();
	public Executor() {
		super();
	}
	
	public static void main(String[] args) throws Exception {
//		URL url = new URL("http://localhost:8080/mep-innser-service-stub/MEPInnerService?wsdl");
//		URLConnection urlConnection = url.openConnection();
//		
//		InputStream is = urlConnection.getInputStream();
//		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//		int data = 0;
//		
//		while ((data = is.read()) != -1) {
//			buffer.write(data);
//		}
//		is.close();
//		ByteArrayInputStream bais = new ByteArrayInputStream(buffer.toByteArray());
//		LOG.info(buffer.toString());
//		buffer.close();
//		
//		Executor e = new Executor();
//		XMLReader xr = XMLReaderFactory.createXMLReader();
//		xr.setContentHandler(e);
//		xr.setErrorHandler(e);
//		xr.parse(new InputSource(bais));
//		
//		for (String s : e.getXSDURIList()) {
//			LOG.info(getURLContent(new URL(s)));
//		}
		
		final int range = 10;
		RandomGenerator randomGenerator = new RandomGenerator();
		randomGenerator.setNumberRange(range);
		
		RandomThread t1 = new RandomThread(1, randomGenerator);
		RandomThread t2 = new RandomThread(2, randomGenerator);
		RandomThread t3 = new RandomThread(3, randomGenerator);
		
		t1.start();
		t2.start();
		t3.start();
		
//		Random rand = new Random();
//		for (int i = 0; i < 100; i++) {
//			System.out.println(rand.nextInt(10));
//		}
	}
	
	public static String getURLContent(URL url) throws IOException {
		URLConnection urlConnection = url.openConnection();
		
		InputStream is = urlConnection.getInputStream();
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int data = 0;
		
		while ((data = is.read()) != -1) {
			buffer.write(data);
		}
		is.close();
		return buffer.toString("UTF8");
	}
	
    public void startDocument () {
    	System.out.println("Start document");
    }

    public void endDocument () {
    	System.out.println("End document");
    }
    
    public void startElement (String uri, String name, String qName, Attributes atts) {
    	if ("import".equals(name)) {
    		handleAtts(atts);
    	}
	}
    
    private void handleAtts(Attributes atts) {
    	for (int index = 0; index < atts.getLength(); index++) {
    		if ("schemaLocation".equals(atts.getLocalName(index))) {
    			xsdURIList.add(atts.getValue(index));
    		}
    	}
    }
    
    public List<String> getXSDURIList() {
    	return xsdURIList;
    }
}

