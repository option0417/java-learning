package tw.com.wd.jersey;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;


@Retention(RetentionPolicy.RUNTIME)
@Target(METHOD)
public @interface Random {
	public int values() default 0;
}