package op.sample.spring.seqgenerator;

public interface SequenceDao {
	public Sequence getSequence(String _sequenceId);
	public int getNextValue(String _sequenceId);
}
