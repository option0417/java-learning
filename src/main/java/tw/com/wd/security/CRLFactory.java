package tw.com.wd.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CRL;
import java.security.cert.CRLException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.List;

public class CRLFactory {
	private CertificateFactory certificateFactory;	
	
	public CRLFactory(String type) throws CertificateException {
		certificateFactory = CertificateFactory.getInstance(type);
	}
	
	public List<CRL> getCRLs(InputStream inStream) throws CRLException, IOException {
		List<CRL> crlList = new ArrayList<CRL>();
		
		while (inStream.available() > 0) {
			crlList.add(certificateFactory.generateCRL(inStream));
		}
		
		return crlList;
	}
}
