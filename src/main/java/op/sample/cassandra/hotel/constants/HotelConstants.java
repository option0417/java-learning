package op.sample.cassandra.hotel.constants;

import org.apache.cassandra.thrift.ConsistencyLevel;

public class HotelConstants {
	public static final String CAMBRIA_NAME = "Cambria Suites Hayden";
	public static final String CLARION_NAME = "Clarion Scottsdale Peak";
	public static final String W_NAME = "The W SF";
	public static final String WALDORF_NAME = "The Waldorf=Astoria";
	public static final String UTF8 = "UTF-8";
	public static final String KEYSPACE = "Hotelier";
	public static final ConsistencyLevel CL = ConsistencyLevel.ONE;
	public static final String HOST = "localhost";
	public static final int PORT = 9160;
}
