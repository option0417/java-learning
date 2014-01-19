package op.sample.cassandra.hotel.service;


import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import op.sample.cassandra.hotel.constants.HotelConstants;
import op.sample.cassandra.hotel.utils.Connector;

import org.apache.cassandra.thrift.Cassandra;
import org.apache.cassandra.thrift.Column;
import org.apache.cassandra.thrift.ColumnOrSuperColumn;
import org.apache.cassandra.thrift.ColumnParent;
import org.apache.cassandra.thrift.ColumnPath;
import org.apache.cassandra.thrift.Mutation;
import org.apache.cassandra.thrift.SuperColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Performs the initial population of the database. 
 * Fills the CFs and SCFs with Hotel, Point of Interest, and index data.
 * Shows batch_mutate and insert for Column Families and Super Column Families.
 * 
 * I am totally ignoring exceptions to save space.
 */
public class Prepopulate {
	private static final Logger LOG = LoggerFactory.getLogger(Prepopulate.class);
	
	
	private Cassandra.Client client;
	private Connector connector;
	
	//constructor opens a connection so we don't have to 
	//constantly recreate it
	public Prepopulate() throws Exception {
		connector = new Connector();
		client = connector.connect();
	}

	public void prepopulate() throws Exception {
		try {
			//pre-populate the DB with Hotels
			insertAllHotels();
			
			//also add all hotels to index to help searches
			insertByCityIndexes();
			
			//pre-populate the DB with POIs
			insertAllPointsOfInterest();
		} finally {		
			connector.close();
		}
	}
	
	//also add hotels to lookup by city index
	public void insertByCityIndexes() throws Exception  {
	
		String scottsdaleKey = "Scottsdale:AZ"; 
		String sfKey = "San Francisco:CA"; 
		String newYorkKey = "New York:NY"; 
					
		insertByCityIndex(scottsdaleKey, HotelConstants.CAMBRIA_NAME);
		insertByCityIndex(scottsdaleKey, HotelConstants.CLARION_NAME);
		insertByCityIndex(sfKey, HotelConstants.W_NAME);
		insertByCityIndex(newYorkKey, HotelConstants.WALDORF_NAME);
}
	//use Valueless Column pattern
	private void insertByCityIndex(String rowKey, String hotelName)
		throws Exception {		
		Column nameCol = new Column().setName(hotelName.getBytes(HotelConstants.UTF8));
		
		ColumnOrSuperColumn nameCosc = new ColumnOrSuperColumn();
		nameCosc.column = nameCol;
		
		Mutation nameMut = new Mutation();
		nameMut.column_or_supercolumn = nameCosc;

		//set up the batch
		Map<String, Map<String, List<Mutation>>> mutationMap = 
			new HashMap<String, Map<String, List<Mutation>>>();
		
		Map<String, List<Mutation>> muts = 
			new HashMap<String, List<Mutation>>();
		List<Mutation> cols = new ArrayList<Mutation>();
		cols.add(nameMut);

		String columnFamily = "HotelByCity";
		muts.put(columnFamily, cols);
		
		//outer map key is a row key
		//inner map key is the column family name
		mutationMap.put(rowKey, muts);
		
		
		//create representation of the column
		ColumnPath cp = new ColumnPath(columnFamily);
		cp.setColumn(hotelName.getBytes(HotelConstants.UTF8));
		
		ColumnParent parent = new ColumnParent(columnFamily);
		//here, the column name IS the value (there's no value)
		Column col = createColumn(hotelName, "");
		
		client.insert(ByteBuffer.wrap(rowKey.getBytes()), parent, col, HotelConstants.CL);
				
		LOG.debug("Inserted HotelByCity index for " + hotelName);
		
	} //end inserting ByCity index

	//POI
	public void insertAllPointsOfInterest() throws Exception {

		LOG.debug("Inserting POIs.");
					
		insertPOIEmpireState();
		insertPOICentralPark();
		insertPOIPhoenixZoo();
		insertPOISpringTraining();
		
		LOG.debug("Done inserting POIs.");
	}

	private void insertPOISpringTraining() throws Exception {
		//Map<byte[],Map<String,List<Mutation>>>
		Map<ByteBuffer, Map<String, List<Mutation>>> outerMap = 
			new HashMap<ByteBuffer, Map<String, List<Mutation>>>();
		List<Mutation> columnsToAdd = new ArrayList<Mutation>();
		
		String keyName = "Spring Training";
		Column descCol = createColumn("desc", "Fun for baseball fans.");
		
		Column phoneCol = createColumn("phone", "623-333-3333");
		
		List<Column> cols = new ArrayList<Column>();
		cols.add(descCol);
		cols.add(phoneCol);
		
		Map<String, List<Mutation>> innerMap = 
			new HashMap<String, List<Mutation>>();
		
		Mutation columns = new Mutation();
		ColumnOrSuperColumn descCosc = new ColumnOrSuperColumn();
		SuperColumn sc = new SuperColumn();
		sc.setName(HotelConstants.CAMBRIA_NAME.getBytes(HotelConstants.UTF8));
		sc.columns = cols;
		
		descCosc.super_column = sc;
		columns.setColumn_or_supercolumn(descCosc);
		
		columnsToAdd.add(columns);
		
		String superCFName = "PointOfInterest";
		ColumnPath cp = new ColumnPath();
		cp.column_family = superCFName;
		cp.setSuper_column(HotelConstants.CAMBRIA_NAME.getBytes());
		cp.setSuper_columnIsSet(true);
		
		innerMap.put(superCFName, columnsToAdd);
		outerMap.put(ByteBuffer.wrap(keyName.getBytes()), innerMap);
		
		client.batch_mutate(outerMap, HotelConstants.CL);
				
		LOG.debug("Done inserting Spring Training.");
	}


	private void insertPOIPhoenixZoo() throws Exception {
		
		Map<ByteBuffer, Map<String, List<Mutation>>> outerMap = 
			new HashMap<ByteBuffer, Map<String, List<Mutation>>>();
		List<Mutation> columnsToAdd = new ArrayList<Mutation>();
		
		String keyName = "Phoenix Zoo";
		Column descCol = createColumn("desc", "They have animals here.");
		Column phoneCol = createColumn("phone", "480-555-9999");
		
		List<Column> cols = new ArrayList<Column>();
		cols.add(descCol);
		cols.add(phoneCol);
		
		Map<String, List<Mutation>> innerMap = 
			new HashMap<String, List<Mutation>>();
		
		String cambriaName = "Cambria Suites Hayden";
		
		Mutation columns = new Mutation();
		ColumnOrSuperColumn descCosc = new ColumnOrSuperColumn();
		SuperColumn sc = new SuperColumn()
			.setName(cambriaName.getBytes())
			.setColumns(cols);
		
		descCosc.super_column = sc;
		columns.setColumn_or_supercolumn(descCosc);
		
		columnsToAdd.add(columns);
		
		String superCFName = "PointOfInterest";
		ColumnPath cp = new ColumnPath();
		cp.column_family = superCFName;
		cp.setSuper_column(cambriaName.getBytes());
		cp.setSuper_columnIsSet(true);
		
		innerMap.put(superCFName, columnsToAdd);
		outerMap.put(ByteBuffer.wrap(keyName.getBytes()), innerMap);
		
		client.batch_mutate(outerMap, HotelConstants.CL);
				
		LOG.debug("Done inserting Phoenix Zoo.");
	}


	private void insertPOICentralPark() throws Exception {
					
		Map<ByteBuffer, Map<String, List<Mutation>>> outerMap = 
			new HashMap<ByteBuffer, Map<String, List<Mutation>>>();
		List<Mutation> columnsToAdd = new ArrayList<Mutation>();
		
		String keyName = "Central Park";
		Column descCol = createColumn("desc", "Walk around in the park. It's pretty.");

		//no phone column for park
		
		List<Column> cols = new ArrayList<Column>();
		cols.add(descCol);
		
		Map<String, List<Mutation>> innerMap = 
			new HashMap<String, List<Mutation>>();
		
		Mutation columns = new Mutation();
		ColumnOrSuperColumn descCosc = new ColumnOrSuperColumn();
		SuperColumn waldorfSC = new SuperColumn()
			.setName(HotelConstants.WALDORF_NAME.getBytes())
			.setColumns(cols);
		
		descCosc.super_column = waldorfSC;
		columns.setColumn_or_supercolumn(descCosc);
		
		columnsToAdd.add(columns);
		
		String superCFName = "PointOfInterest";
		ColumnPath cp = new ColumnPath();
		cp.column_family = superCFName;
		cp.setSuper_column(HotelConstants.WALDORF_NAME.getBytes());
		cp.setSuper_columnIsSet(true);
		
		innerMap.put(superCFName, columnsToAdd);
		outerMap.put(ByteBuffer.wrap(keyName.getBytes()), innerMap);

		client.batch_mutate(outerMap, HotelConstants.CL);
				
		LOG.debug("Done inserting Central Park.");
	}

	private void insertPOIEmpireState()  throws Exception {
		
		Map<ByteBuffer, Map<String, List<Mutation>>> outerMap = 
			new HashMap<ByteBuffer, Map<String, List<Mutation>>>();
		
		List<Mutation> columnsToAdd = new ArrayList<Mutation>();
		
		String esbName = "Empire State Building";
		Column descCol = createColumn("desc", "Great view from 102nd floor.");
		Column phoneCol = createColumn("phone", "212-777-7777");
		
		List<Column> esbCols = new ArrayList<Column>();
		esbCols.add(descCol);
		esbCols.add(phoneCol);
		
		Map<String, List<Mutation>> innerMap = new HashMap<String, List<Mutation>>();
					
		Mutation columns = new Mutation();
		ColumnOrSuperColumn descCosc = new ColumnOrSuperColumn();
		SuperColumn waldorfSC = new SuperColumn()
			.setName(HotelConstants.WALDORF_NAME.getBytes(HotelConstants.UTF8))
			.setColumns(esbCols);
		
		descCosc.super_column = waldorfSC;
		columns.setColumn_or_supercolumn(descCosc);
		
		columnsToAdd.add(columns);
		
		String superCFName = "PointOfInterest";
		ColumnPath cp = new ColumnPath();
		cp.column_family = superCFName;
		cp.setSuper_column(HotelConstants.WALDORF_NAME.getBytes());
		cp.setSuper_columnIsSet(true);
		
		innerMap.put(superCFName, columnsToAdd);
		outerMap.put(ByteBuffer.wrap(esbName.getBytes()), innerMap);
		
		client.batch_mutate(outerMap, HotelConstants.CL);
				
		LOG.debug("Done inserting Empire State.");
	}
	
	//convenience method runs all of the individual inserts
	public void insertAllHotels() throws Exception {
		
		String columnFamily = "Hotel";	
		
		//row keys
		String cambriaKey = "AZC_043"; 
		String clarionKey = "AZS_011"; 
		String wKey = "CAS_021"; 
		String waldorfKey = "NYN_042"; 
		
		//conveniences
		Map<ByteBuffer, Map<String, List<Mutation>>> cambriaMutationMap = 
			createCambriaMutation(columnFamily, cambriaKey);
		
		Map<ByteBuffer, Map<String, List<Mutation>>> clarionMutationMap = 
			createClarionMutation(columnFamily, clarionKey);
		
		Map<ByteBuffer, Map<String, List<Mutation>>> waldorfMutationMap = 
			createWaldorfMutation(columnFamily, waldorfKey);

		Map<ByteBuffer, Map<String, List<Mutation>>> wMutationMap = 
			createWMutation(columnFamily, wKey);
			
		client.batch_mutate(cambriaMutationMap, HotelConstants.CL);
		LOG.debug("Inserted " + cambriaKey);
		client.batch_mutate(clarionMutationMap, HotelConstants.CL);
		LOG.debug("Inserted " + clarionKey);
		client.batch_mutate(wMutationMap, HotelConstants.CL);
		LOG.debug("Inserted " + wKey);
		client.batch_mutate(waldorfMutationMap, HotelConstants.CL);
		LOG.debug("Inserted " + waldorfKey);
				
		LOG.debug("Done inserting at " + System.nanoTime());
	}

	//set up columns to insert for W
	private Map<ByteBuffer, Map<String, List<Mutation>>> createWMutation(
			String columnFamily, String rowKey)
			throws UnsupportedEncodingException {		
		Column nameCol = createColumn("name", HotelConstants.W_NAME);
		Column phoneCol = createColumn("phone", "415-222-2222");
		Column addressCol = createColumn("address", "181 3rd Street");
		Column cityCol = createColumn("city", "San Francisco");
		Column stateCol = createColumn("state", "CA");
		Column zipCol = createColumn("zip", "94103");
		
		ColumnOrSuperColumn nameCosc = new ColumnOrSuperColumn();
		nameCosc.column = nameCol;
		
		ColumnOrSuperColumn phoneCosc = new ColumnOrSuperColumn();
		phoneCosc.column = phoneCol;
		
		ColumnOrSuperColumn addressCosc = new ColumnOrSuperColumn();
		addressCosc.column = addressCol;

		ColumnOrSuperColumn cityCosc = new ColumnOrSuperColumn();
		cityCosc.column = cityCol;

		ColumnOrSuperColumn stateCosc = new ColumnOrSuperColumn();
		stateCosc.column = stateCol;
		
		ColumnOrSuperColumn zipCosc = new ColumnOrSuperColumn();
		zipCosc.column = zipCol;
		
		Mutation nameMut = new Mutation();
		nameMut.column_or_supercolumn = nameCosc;
		Mutation phoneMut = new Mutation();
		phoneMut.column_or_supercolumn = phoneCosc;
		Mutation addressMut = new Mutation();
		addressMut.column_or_supercolumn = addressCosc;
		Mutation cityMut = new Mutation();
		cityMut.column_or_supercolumn = cityCosc;
		Mutation stateMut = new Mutation();
		stateMut.column_or_supercolumn = stateCosc;
		Mutation zipMut = new Mutation();
		zipMut.column_or_supercolumn = zipCosc;

		//set up the batch
		Map<ByteBuffer, Map<String, List<Mutation>>> mutationMap = 
			new HashMap<ByteBuffer, Map<String, List<Mutation>>>();
		
		Map<String, List<Mutation>> muts = 
			new HashMap<String, List<Mutation>>();
		List<Mutation> cols = new ArrayList<Mutation>();
		cols.add(nameMut);
		cols.add(phoneMut);
		cols.add(addressMut);
		cols.add(cityMut);
		cols.add(stateMut);
		cols.add(zipMut);

		muts.put(columnFamily, cols);
		
		//outer map key is a row key
		//inner map key is the column family name
		mutationMap.put(ByteBuffer.wrap(rowKey.getBytes()), muts);
		return mutationMap;
	}

	//add Waldorf hotel to Hotel CF
	private Map<ByteBuffer, Map<String, List<Mutation>>> createWaldorfMutation(
			String columnFamily, String rowKey)
			throws UnsupportedEncodingException {
		
		Column nameCol = createColumn("name", HotelConstants.WALDORF_NAME);
		Column phoneCol = createColumn("phone", "212-555-5555");
		Column addressCol = createColumn("address", "301 Park Ave");
		Column cityCol = createColumn("city", "New York");
		Column stateCol = createColumn("state", "NY");
		Column zipCol = createColumn("zip", "10019");
		
		ColumnOrSuperColumn nameCosc = new ColumnOrSuperColumn();
		nameCosc.column = nameCol;
		
		ColumnOrSuperColumn phoneCosc = new ColumnOrSuperColumn();
		phoneCosc.column = phoneCol;
		
		ColumnOrSuperColumn addressCosc = new ColumnOrSuperColumn();
		addressCosc.column = addressCol;

		ColumnOrSuperColumn cityCosc = new ColumnOrSuperColumn();
		cityCosc.column = cityCol;

		ColumnOrSuperColumn stateCosc = new ColumnOrSuperColumn();
		stateCosc.column = stateCol;
		
		ColumnOrSuperColumn zipCosc = new ColumnOrSuperColumn();
		zipCosc.column = zipCol;
		
		Mutation nameMut = new Mutation();
		nameMut.column_or_supercolumn = nameCosc;
		Mutation phoneMut = new Mutation();
		phoneMut.column_or_supercolumn = phoneCosc;
		Mutation addressMut = new Mutation();
		addressMut.column_or_supercolumn = addressCosc;
		Mutation cityMut = new Mutation();
		cityMut.column_or_supercolumn = cityCosc;
		Mutation stateMut = new Mutation();
		stateMut.column_or_supercolumn = stateCosc;
		Mutation zipMut = new Mutation();
		zipMut.column_or_supercolumn = zipCosc;

		//set up the batch
		Map<ByteBuffer, Map<String, List<Mutation>>> mutationMap = 
			new HashMap<ByteBuffer, Map<String, List<Mutation>>>();
		
		Map<String, List<Mutation>> muts = 
			new HashMap<String, List<Mutation>>();
		List<Mutation> cols = new ArrayList<Mutation>();
		cols.add(nameMut);
		cols.add(phoneMut);
		cols.add(addressMut);
		cols.add(cityMut);
		cols.add(stateMut);
		cols.add(zipMut);

		muts.put(columnFamily, cols);
		
		//outer map key is a row key
		//inner map key is the column family name
		mutationMap.put(ByteBuffer.wrap(rowKey.getBytes()), muts);
		return mutationMap;
	}
	
	//set up columns to insert for Clarion
 private Map<ByteBuffer, Map<String, List<Mutation>>> createClarionMutation(
			String columnFamily, String rowKey)
			throws UnsupportedEncodingException {		
		Column nameCol = createColumn("name", HotelConstants.CLARION_NAME);
		Column phoneCol = createColumn("phone", "480-333-3333");
		Column addressCol = createColumn("address", "3000 N. Scottsdale Rd");
		Column cityCol = createColumn("city", "Scottsdale");
		Column stateCol = createColumn("state", "AZ");
		Column zipCol = createColumn("zip", "85255");
		
		ColumnOrSuperColumn nameCosc = new ColumnOrSuperColumn();
		nameCosc.column = nameCol;
		
		ColumnOrSuperColumn phoneCosc = new ColumnOrSuperColumn();
		phoneCosc.column = phoneCol;
		
		ColumnOrSuperColumn addressCosc = new ColumnOrSuperColumn();
		addressCosc.column = addressCol;

		ColumnOrSuperColumn cityCosc = new ColumnOrSuperColumn();
		cityCosc.column = cityCol;

		ColumnOrSuperColumn stateCosc = new ColumnOrSuperColumn();
		stateCosc.column = stateCol;
		
		ColumnOrSuperColumn zipCosc = new ColumnOrSuperColumn();
		zipCosc.column = zipCol;
		
		Mutation nameMut = new Mutation();
		nameMut.column_or_supercolumn = nameCosc;
		Mutation phoneMut = new Mutation();
		phoneMut.column_or_supercolumn = phoneCosc;
		Mutation addressMut = new Mutation();
		addressMut.column_or_supercolumn = addressCosc;
		Mutation cityMut = new Mutation();
		cityMut.column_or_supercolumn = cityCosc;
		Mutation stateMut = new Mutation();
		stateMut.column_or_supercolumn = stateCosc;
		Mutation zipMut = new Mutation();
		zipMut.column_or_supercolumn = zipCosc;

		//set up the batch
		Map<ByteBuffer, Map<String, List<Mutation>>> mutationMap = 
			new HashMap<ByteBuffer, Map<String, List<Mutation>>>();
		
		Map<String, List<Mutation>> muts = 
			new HashMap<String, List<Mutation>>();
		List<Mutation> cols = new ArrayList<Mutation>();
		cols.add(nameMut);
		cols.add(phoneMut);
		cols.add(addressMut);
		cols.add(cityMut);
		cols.add(stateMut);
		cols.add(zipMut);

		muts.put(columnFamily, cols);
		
		//outer map key is a row key
		//inner map key is the column family name
		mutationMap.put(ByteBuffer.wrap(rowKey.getBytes()), muts);
		return mutationMap;
	}

	//set up columns to insert for Cambria
	private Map<ByteBuffer, Map<String, List<Mutation>>> createCambriaMutation(
			String columnFamily, String cambriaKey)
			throws UnsupportedEncodingException {
		
		//set up columns for Cambria		
		Column cambriaNameCol = createColumn("name", "Cambria Suites Hayden");
		Column cambriaPhoneCol = createColumn("phone", "480-444-4444");
		Column cambriaAddressCol = createColumn("address", "400 N. Hayden");
		Column cambriaCityCol = createColumn("city", "Scottsdale");
		Column cambriaStateCol = createColumn("state", "AZ");
		Column cambriaZipCol = createColumn("zip", "85255");
		
		ColumnOrSuperColumn nameCosc = new ColumnOrSuperColumn();
		nameCosc.column = cambriaNameCol;
		
		ColumnOrSuperColumn phoneCosc = new ColumnOrSuperColumn();
		phoneCosc.column = cambriaPhoneCol;
		
		ColumnOrSuperColumn addressCosc = new ColumnOrSuperColumn();
		addressCosc.column = cambriaAddressCol;

		ColumnOrSuperColumn cityCosc = new ColumnOrSuperColumn();
		cityCosc.column = cambriaCityCol;

		ColumnOrSuperColumn stateCosc = new ColumnOrSuperColumn();
		stateCosc.column = cambriaStateCol;
		
		ColumnOrSuperColumn zipCosc = new ColumnOrSuperColumn();
		zipCosc.column = cambriaZipCol;
		
		Mutation nameMut = new Mutation();
		nameMut.column_or_supercolumn = nameCosc;
		Mutation phoneMut = new Mutation();
		phoneMut.column_or_supercolumn = phoneCosc;
		Mutation addressMut = new Mutation();
		addressMut.column_or_supercolumn = addressCosc;
		Mutation cityMut = new Mutation();
		cityMut.column_or_supercolumn = cityCosc;
		Mutation stateMut = new Mutation();
		stateMut.column_or_supercolumn = stateCosc;
		Mutation zipMut = new Mutation();
		zipMut.column_or_supercolumn = zipCosc;

		//set up the batch
		Map<ByteBuffer, Map<String, List<Mutation>>> cambriaMutationMap = 
			new HashMap<ByteBuffer, Map<String, List<Mutation>>>();
			
		Map<String, List<Mutation>> cambriaMuts = 
			new HashMap<String, List<Mutation>>();
		List<Mutation> cambriaCols = new ArrayList<Mutation>();
		cambriaCols.add(nameMut);
		cambriaCols.add(phoneMut);
		cambriaCols.add(addressMut);
		cambriaCols.add(cityMut);
		cambriaCols.add(stateMut);
		cambriaCols.add(zipMut);

		cambriaMuts.put(columnFamily, cambriaCols);
		
		//outer map key is a row key
		//inner map key is the column family name
		cambriaMutationMap.put(ByteBuffer.wrap(cambriaKey.getBytes()), cambriaMuts);
		return cambriaMutationMap;
	}
	
	private Column createColumn(String name, String value) throws UnsupportedEncodingException {
		return new Column()
			.setName(name.getBytes(HotelConstants.UTF8))
			.setValue(value.getBytes(HotelConstants.UTF8))
			.setTimestamp(System.currentTimeMillis());
	}
}
