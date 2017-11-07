package tw.com.wd.spring.refbean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataPrefixGenerator implements PrefixGenerator {
	private DateFormat formatter;
	
	public void setPattern(String _pattern) {
		formatter = new SimpleDateFormat( _pattern );
	}
	
	public String getPrefix() {
		return formatter.format(new Date());
	}
}
