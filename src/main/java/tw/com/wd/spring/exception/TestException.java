package tw.com.wd.spring.exception;

import tw.com.wd.spring.annotation.LogException;

import org.springframework.stereotype.Service;

@Service
public class TestException {
	@LogException
	public void testException() throws Exception {
		System.out.println("TestProString");
		String str = null;		
		//System.out.println(str.toString());
		throw new RuntimeException("TestException");
	}
}
