package tw.com.wd.spring.dao.impl;

import tw.com.wd.spring.dao.AbstractDAO;
import tw.com.wd.spring.dao.IBitOrganizerDAO;
import tw.com.wd.spring.domain.BitOrganizer;

import org.springframework.stereotype.Repository;

@Repository
public class BitOrganizerDAO extends AbstractDAO<BitOrganizer, Long> implements IBitOrganizerDAO {

}
