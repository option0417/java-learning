package op.sample.msgholder;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;


/**
 * 200 : 設定成功
 * 400 : 密碼檢核不符
 * 401 : 認證錯誤
 * 404 : 參數不完整
 */
public class PasswordMsgHolder extends AbstractMsgHolder implements StatusMsgHolder {
	private static final Map<Integer, String> PWD_RSPMSG_MAP = new HashMap<Integer, String>();
	
	static {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("status_msg/password_service", Locale.TAIWAN);
		PWD_RSPMSG_MAP.put(Integer.valueOf(200), resourceBundle.getString("200"));
		PWD_RSPMSG_MAP.put(Integer.valueOf(400), resourceBundle.getString("400"));
		PWD_RSPMSG_MAP.put(Integer.valueOf(401), resourceBundle.getString("401"));
		PWD_RSPMSG_MAP.put(Integer.valueOf(404), resourceBundle.getString("404"));
	}
	
	protected PasswordMsgHolder() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected Class<PasswordMsgHolder> getStatusMsgHolderType() {
		return PasswordMsgHolder.class;
	}

	@Override
	protected String getInstanceStatusMsg(int statusCode) {
		return PWD_RSPMSG_MAP.get(statusCode);
	}
}
