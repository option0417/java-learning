package tw.com.wd.spring.service;

import tw.com.wd.spring.domain.BitOrganizer;

public interface IBitOrganizerService {
	/**
	 * Create BitOrganizer to Database
	 * @param bitOrganizer
	 * @return BitOrganizer
	 */
	public BitOrganizer create(BitOrganizer bitOrganizer);
	
	/**
	 * Find BitOrganizer in Database by PK
	 * @param clazz
	 * @param pk
	 * @return BitOrganizer
	 */
	public BitOrganizer find(Class<BitOrganizer> clazz, Long pk);
	
	/**
	 * Update BitOrganizer to Database
	 * @param bitOrganizer
	 */
	public void update(BitOrganizer bitOrganizer);
	
	/**
	 * Delete BitOrganizer from Database
	 * @param bitOrganizer
	 */
	public void delete(BitOrganizer bitOrganizer);
}
