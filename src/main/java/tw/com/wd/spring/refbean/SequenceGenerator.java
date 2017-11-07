package tw.com.wd.spring.refbean;

public class SequenceGenerator {
	private PrefixGenerator prefixGenerator;
	private String suffix;
	
	public void setPrefixGenerator(PrefixGenerator _PrefixGenerator) {	prefixGenerator = _PrefixGenerator;	}
	
	public void setSuffix(String _suffix)	{	suffix = _suffix;	}
	
	public String getSequence() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(prefixGenerator.getPrefix());
		buffer.append(suffix);
		return buffer.toString();
	}
}
