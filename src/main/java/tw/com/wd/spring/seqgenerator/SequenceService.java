package tw.com.wd.spring.seqgenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SequenceService {
	
	@Autowired
	private SequenceDao sequenceDao;
	
	public void setSequenceDao(SequenceDao _sequenceDao)	{ sequenceDao = _sequenceDao;	}
	
	public String generate(String _sequenceId) {
		Sequence tmpSequence = sequenceDao.getSequence(_sequenceId);
		int tmpValue = sequenceDao.getNextValue(_sequenceId);		
		return tmpSequence.getPrefix() + tmpValue + tmpSequence.getSuffix();
	}
}
