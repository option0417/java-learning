package op.sample.sync;

public abstract class SyncObject<ENTITY, PK> implements SyncTransAPI<ENTITY>{
	protected PK pk;
	protected int id;
	
	public void setPK(PK pk) {
		this.pk = pk;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public PK getPK() {
		return this.pk;
	}
	
	public int getID() {
		return this.id;
	}
}
