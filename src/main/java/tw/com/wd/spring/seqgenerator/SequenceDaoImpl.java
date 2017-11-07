package tw.com.wd.spring.seqgenerator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class SequenceDaoImpl implements SequenceDao{
	private Map<String, Sequence> sequences;
	private Map<String, Integer> value;
	
	public SequenceDaoImpl() {
		sequences = new HashMap<String, Sequence>();
		value = new HashMap<String, Integer>();
		sequences.put("IT", new Sequence("IT", "30", "A"));
		value.put("IT", 10000);
	}
	
	public Sequence getSequence(String _sequenceId)	{	return sequences.get(_sequenceId);	}
	
	public int getNextValue(String _sequenceId)
	{
		int tmp = value.get(_sequenceId);
		value.put(_sequenceId, tmp+1);
		return tmp;
	}
}
