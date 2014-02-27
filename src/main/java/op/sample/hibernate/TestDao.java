package op.sample.hibernate;

import org.hibernate.Session;

public class TestDao {
	public void insertBySession(TestVO testVO) {
		  Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		  try {
		    session.beginTransaction();
		    session.save(testVO);
		    session.getTransaction().commit();
		  } catch (RuntimeException ex) {
		    session.getTransaction().rollback();
		    throw ex;
		  }
	}
}
