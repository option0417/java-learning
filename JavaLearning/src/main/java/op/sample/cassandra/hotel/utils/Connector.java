package op.sample.cassandra.hotel.utils;

import op.sample.cassandra.hotel.constants.HotelConstants;

import org.apache.cassandra.thrift.Cassandra.Client;
import org.apache.cassandra.thrift.InvalidRequestException;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class Connector {
	private TTransport tr = new TSocket(HotelConstants.HOST, HotelConstants.PORT);
	
	public Client connect() throws InvalidRequestException, TException {
		TFramedTransport tf = new TFramedTransport(tr);
		TProtocol proto = new TBinaryProtocol(tf);
		Client client = new Client(proto);
		tr.open();
		client.set_keyspace(HotelConstants.KEYSPACE);
		return client;
	}
	
	public void close() {
		tr.close();
	}
}
