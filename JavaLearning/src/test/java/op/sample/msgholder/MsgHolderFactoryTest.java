package op.sample.msgholder;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import op.sample.msgholder.MsgHolderFactory;
import op.sample.msgholder.StatusMsgHolder;

import org.junit.Test;

public class MsgHolderFactoryTest {

	@Test
	public void testGetHolder() {
		Exception rtnException = null;
		StatusMsgHolder statusHolder = null;
		String passwordService = "PasswordService";
		
		try {
			statusHolder = MsgHolderFactory.getHolder(passwordService);
		} catch (Exception e) {
			rtnException = e;
		}
		
		assertThat(rtnException, is(nullValue()));
		assertThat(statusHolder, is(notNullValue()));
		assertThat(statusHolder.getClass().getName(), is(equalTo("com.mitake.o.servicemodule.msgholder.PasswordMsgHolder")));
	}

}
