package tw.com.wd.spring.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDAO<ENTITY, PK extends Serializable>
	implements IDAO<ENTITY, PK> {
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public ENTITY create(ENTITY entity) {
		return (ENTITY)getCurrentSession().save(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ENTITY find(Class<ENTITY> clazz, PK pk) {
		return (ENTITY)getCurrentSession().get(clazz, pk);
	}

	@Override
	public void update(ENTITY entity) {
		getCurrentSession().update(entity);
	}

	@Override
	public void delete(ENTITY entity) {
		getCurrentSession().delete(entity);
	}
	
	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}
