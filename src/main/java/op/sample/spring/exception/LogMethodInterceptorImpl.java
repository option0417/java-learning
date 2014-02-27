package op.sample.spring.exception;

import org.aopalliance.intercept.MethodInvocation;

public class LogMethodInterceptorImpl implements LogMethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		try {
			System.out.println("Method : "+ invocation.getMethod().getName());
			System.out.println("Method : "+ invocation.getThis().toString());
			System.out.println("Method : "+ invocation.getArguments().toString());
			return invocation.proceed();
		} catch (Throwable e) {
			RuntimeException re = (RuntimeException)e;
			
			System.out.println("Catch EXception");
			System.out.println(re.getMessage());
			return null;
		}
	}
}
