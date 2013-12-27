package op.sample.msgholder;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.inject.Singleton;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mitake.o.servicemodule.api.PasswordService;
import com.mitake.o.servicemodule.msgholder.PasswordMsgHolder;
import com.mitake.o.servicemodule.msgholder.StatusMsgHolder;
import com.mitake.o.servicemodule.validator.HeaderValidator;
import com.mitake.o.servicemodule.validator.impl.PasswordHeaderValidator;

@Singleton
public class PasswordServiceTMPImpl implements PasswordService {
	private static final Logger LOG = LoggerFactory.getLogger(PasswordService.class);

	@Override
	public Response setPassword(HttpHeaders httpHeader) {
		LOG.debug("===== Start of login =====");
		HeaderValidator headerValidator = new PasswordHeaderValidator();
		StatusMsgHolder statusMsgHolder = new PasswordMsgHolder();
		if (headerValidator.vaildParameters(httpHeader)) {
			MultivaluedMap<String, String> map = httpHeader.getRequestHeaders();		
			Iterator<Entry<String, List<String>>> iter = map.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<String, List<String>> entry = iter.next();
				LOG.debug("Key: {}", entry.getKey());
				LOG.debug("Value: {}", entry.getValue());
			}
			
			return Response.status(200).entity(statusMsgHolder.getStatusMsg(200)).build();
		} else {
			return Response.status(404).entity(statusMsgHolder.getStatusMsg(404)).build();
		}
	}
}
