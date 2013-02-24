package op.sample.spring.exception;

import op.sample.spring.annotation.LogException;

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
