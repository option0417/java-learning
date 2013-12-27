package op.sample.msgholder;

public class DefaultMsgHolder extends AbstractMsgHolder implements StatusMsgHolder {	
	@Override
	public String getStatusMsg(int statusCode) {
		return "";
	}

	@Override
	protected Class<DefaultMsgHolder> getStatusMsgHolderType() {
		return DefaultMsgHolder.class;
	}

	@Override
	protected String getInstanceStatusMsg(int statusCode) {
		return "";
	}
}
