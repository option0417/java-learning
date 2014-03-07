package op.sample.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularUtil {
	public static boolean isMobilePhone(String phoneNum) {
		
		Pattern mobilePattern = Pattern.compile("^09[0-9]{8}");
		
		Matcher mobileMatcher = mobilePattern.matcher(phoneNum);
		
		return mobileMatcher.matches();
	}
	
	public static void main(String[] args) {
//		System.out.println(isMobilePhone(""));
//		System.out.println(isMobilePhone("0912345678"));
//		System.out.println(isMobilePhone("0023456789"));
//		System.out.println(isMobilePhone("091234567"));
//		System.out.println(isMobilePhone("991234567"));
		
		System.out.println(Pattern.matches("[^abc]*", "abc"));
		System.out.println(Pattern.matches("[^abc]*", "efg"));
		System.out.println(Pattern.matches("[^abc]*", "dab"));
		
	}
}
