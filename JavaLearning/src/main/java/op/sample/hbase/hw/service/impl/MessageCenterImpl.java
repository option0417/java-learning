package op.sample.hbase.hw.service.impl;

import java.util.List;

import op.sample.Executor;
import op.sample.hbase.hw.domain.Message;
import op.sample.hbase.hw.service.MessageCenter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MessageCenterImpl implements MessageCenter {
	private static Logger LOG = LogManager.getLogger(Executor.class);
	
	public List<Message> listTimeline(String userID) {
		try {
			LOG.debug("start listTimeline");
			Configuration hbaseConf = HBaseConfiguration.create();		
			hbaseConf.set("hbase.zookeeper.quorum", "127.0.0.1");
			//hbaseConf.set("hadoop.home.dir", "/home/option0417/Dev/hadoop-2.2.0");
			
			LOG.debug("Table creating");
			HTable table1 = new HTable(hbaseConf, "myTable");
			LOG.debug("Table created");
			
			Get row1Get = new Get("row1".getBytes());
			
			row1Get.addFamily("cf1".getBytes());
			LOG.debug("number of families: " + row1Get.numFamilies());
			
			Result row1Result = table1.get(row1Get);
			LOG.debug("Result: " + row1Result.toString());
			
			String val = new String(row1Result.getValue("cf1".getBytes(), "".getBytes()));
			LOG.debug("row1Result: " + val);
			
			table1.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public boolean postMessage(Message message) {
		// TODO Auto-generated method stub
		return false;
	}

}
