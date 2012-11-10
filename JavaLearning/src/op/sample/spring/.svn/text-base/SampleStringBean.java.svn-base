package op.sample.spring;

import java.util.List;

public class SampleStringBean {
	private String prefix;
	private List<Object> suffix;
	private int num;
	private int cnt;
	private HelloBean hello;
	
	public void setPrefix(String _prefix)		{	prefix = _prefix;	}
	public void setSuffix(List<Object> _suffix) {	suffix = _suffix;	}
	public void setNum(int _num) 				{	num = _num;			}
	public void sethello(HelloBean _hello) 		{	hello = _hello;		}
	
	public String getStrng() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(prefix);
		buffer.append(num + cnt++);
		for(Object _suffix : suffix)
		{
			buffer.append("-");
			buffer.append(_suffix);
			buffer.append(hello.getHelloWord());
		}
			
		return buffer.toString();
	}
	
}
