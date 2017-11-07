package tw.com.wd.spring.seqgenerator;

public interface SequenceDao {
	public Sequence getSequence(String _sequenceId);
	public int getNextValue(String _sequenceId);
}
