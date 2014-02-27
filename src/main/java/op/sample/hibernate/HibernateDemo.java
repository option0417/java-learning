package op.sample.hibernate;

import java.util.Date;

public class HibernateDemo {
	public static void main(String[] args) {
		TestDao dao = new TestDao();
		TestVO testVO = new TestVO();
		
		testVO.setTestchar("ya~~~~");		
		//testVO.setApplytime(new Date());
		//testVO.setReplytime(new Date());		
		testVO.setTestchar2("ya222~~~~");
		dao.insertBySession(testVO);
    }
}
