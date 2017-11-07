package tw.com.wd.spring.seqgenerator;

public class Sequence {
	private String id;
	private String prefix;
	private String suffix;
	
	public Sequence() {}
	public Sequence(String _id, String _prefix, String _suffix) {
		id = _id;
		prefix = _prefix;
		suffix = _suffix;
	}
	
	public void setId(String _id)			{	id = _id;			}
	public void setPrefix(String _prefix)	{	prefix = _prefix;	}
	public void setSuffix(String _suffix)	{	suffix = _suffix;	}
	
	public String getId()		{	return id;		}
	public String getPrefix()	{	return prefix;	}
	public String getSuffix()	{	return suffix;	}
}
