package op.sample.msgholder;

public abstract class AbstractMsgHolder implements StatusMsgHolder {
	private static AbstractMsgHolder INSTANCE = null; 

	protected AbstractMsgHolder() {
		super();
	}
	
	@Override
	public String getStatusMsg(int statusCode) {
		return getInnerInstance().getInstanceStatusMsg(statusCode);
	}
	
	public StatusMsgHolder getInstance() {
		return getInnerInstance();
	}

	private AbstractMsgHolder getInnerInstance() {
		if (INSTANCE == null) {
			synchronized (this) {
				if (INSTANCE == null) {
					try {
						INSTANCE = getStatusMsgHolderType().newInstance();
					} catch (Exception e) {
						e.printStackTrace();
						INSTANCE = new DefaultMsgHolder();
					}
				}
			}
		}
		return INSTANCE;
	}
	
	protected abstract <T extends AbstractMsgHolder> Class<T> getStatusMsgHolderType();
	protected abstract String getInstanceStatusMsg(int statusCode);
}
