package tw.com.wd.spring.service.impl;

import tw.com.wd.spring.dao.impl.BitOrganizerDAO;
import tw.com.wd.spring.domain.BitOrganizer;
import tw.com.wd.spring.service.IBitOrganizerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BitOrganizerService implements IBitOrganizerService {
	@Autowired
	private BitOrganizerDAO bitOrganizerDAO;

	@Override
	public BitOrganizer create(BitOrganizer bitOrganizer) {
		return bitOrganizerDAO.create(bitOrganizer);
	}

	@Override
	public BitOrganizer find(Class<BitOrganizer> clazz, Long pk) {
		return bitOrganizerDAO.find(clazz, pk);
	}

	@Override
	public void update(BitOrganizer bitOrganizer) {
		bitOrganizerDAO.update(bitOrganizer);
	}

	@Override
	public void delete(BitOrganizer bitOrganizer) {
		bitOrganizerDAO.delete(bitOrganizer);
	}
}
