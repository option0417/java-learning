package op.sample.spring.dao;

import java.io.Serializable;

public interface IDAO<ENTITY, PK extends Serializable> {
	/**
	 * Create ENTITY to Database
	 * @param entity
	 * @return ENTITY
	 */
	public ENTITY create(ENTITY entity);
	
	/**
	 * Find ENTITY from Database
	 * @param clazz
	 * @param pk
	 * @return ENTITY
	 */
	public ENTITY find(Class<ENTITY> clazz, PK pk);
	
	/**
	 * Update ENTITY to Database
	 * @param entity
	 */
	public void update(ENTITY entity);
	
	/**
	 * Delete Entity from Database
	 * @param entity
	 */
	public void delete(ENTITY entity);
}
