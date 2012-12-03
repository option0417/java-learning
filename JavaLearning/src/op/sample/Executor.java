package op.sample;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.cert.CRL;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.util.List;

import op.sample.security.CRLFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Executor {
	private static final Logger LOG = LogManager.getLogger(Executor.class);
	public static void main(String[] args) throws Exception {
//		new Prepopulate().prepopulate();
//		LOG.debug("** Database filled. **");
		
		FileInputStream fis = new FileInputStream("./resource/delta.crl");
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		CRLFactory crlFactory = new CRLFactory("X.509");
		List<CRL> crlList = crlFactory.getCRLs(bis);
		
		for (CRL crl : crlList) {
			System.out.println(crl.toString());
			test(crl);
		}
	}
	
	public static void test(CRL crl) {
		X509CRL x509CRL = (X509CRL)crl;
		for (X509CRLEntry crlEntry : x509CRL.getRevokedCertificates()) {
			for (String s : crlEntry.getCriticalExtensionOIDs()) {
				System.out.println("getCriticalExtensionOIDs : " + s);
			}
			
			for (String s : crlEntry.getNonCriticalExtensionOIDs()) {
				System.out.println("getNonCriticalExtensionOIDs : " + s);
			}
			System.out.println(crlEntry.getSerialNumber().toString(16));
			System.out.println(crlEntry.getRevocationDate());
		}
	}
}
