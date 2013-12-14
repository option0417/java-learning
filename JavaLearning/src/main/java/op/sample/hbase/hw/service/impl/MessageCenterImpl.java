package op.sample.hbase.hw.service.impl;

import java.util.List;

import op.sample.hbase.hw.domain.Message;
import op.sample.hbase.hw.service.MessageCenter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;

public class MessageCenterImpl implements MessageCenter {
	
	public static void main(String[] args) {
		MessageCenter messageCenter = new MessageCenterImpl();
		messageCenter.listTimeline("");
	}

	public List<Message> listTimeline(String userID) {
		try {
			Configuration hbaseConf = HBaseConfiguration.create();		
			hbaseConf.set("hbase.zookeeper.quorum", "localhost");
			HTable table1 = new HTable(hbaseConf, "myTable");
			Get row1Get = new Get("row1".getBytes());
			
			row1Get.addFamily("cf1".getBytes());
			System.out.println("number of families: " + row1Get.numFamilies());
			
			Result row1Result = table1.get(row1Get);
			System.out.println("Result: " + row1Result.toString());
			
			String val = new String(row1Result.getValue("cf1".getBytes(), "1a".getBytes()));
			System.out.println("row1Result: " + val);
			
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
