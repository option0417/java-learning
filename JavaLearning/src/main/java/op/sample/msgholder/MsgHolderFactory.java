package op.sample.msgholder;

import java.util.HashMap;
import java.util.Map;

public class MsgHolderFactory {
	private static final Map<String, Class<?>> MSG_HOLDER_MAP = new HashMap<String, Class<?>>();
	
	
	public static StatusMsgHolder getHolder(String accessService) {
		return MSG_HOLDER_MAP.get(accessService);
	}
	
	public static <T extends StatusMsgHolder>  void register(String accessService, Class<T> TypeOfStatusMsgHolder) {
		MSG_HOLDER_MAP.put(accessService, TypeOfStatusMsgHolder);
	}
}
