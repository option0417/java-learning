package op.sample.spring.dao.impl;

import op.sample.spring.dao.AbstractDAO;
import op.sample.spring.dao.IBitOrganizerDAO;
import op.sample.spring.domain.BitOrganizer;

import org.springframework.stereotype.Repository;

@Repository
public class BitOrganizerDAO extends AbstractDAO<BitOrganizer, Long> implements IBitOrganizerDAO {

}
