package tw.com.wd.utils;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class DataUtils {
	public static final String ENCODING_UTF8 = "UTF-8";

	public static byte[] getUTF8Bytes(String plainText) {
		try {
			return plainText.getBytes(ENCODING_UTF8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return plainText.getBytes();
		}
	}
	
	// Big-Endian byte order
	public static byte[] getLongBytes(long value) {
		return ByteBuffer.allocate(8).putLong(value).array();
	}
	
	public static byte[] getBooleanBytes(boolean value) {
		return getUTF8Bytes(Boolean.toString(value));
	}

	public static byte[] toBytes(long val) {
		byte [] b = new byte[8];
		for (int i = 7; i > 0; i--) {
	      b[i] = (byte) val;
	      val >>>= 8;
	    }
	    b[0] = (byte) val;
	    return b;
	}
  
	public static long toLong(byte[] longBytes) {
	  long lVal = 0;
	  for (int i = 0; i < 7; i++) {
		  lVal += (long)longBytes[i];
		  lVal <<= 8;
	  }
	  lVal += (long)longBytes[7];
	  return lVal;
	}
  
	public static long toLong2(byte[] longBytes) {
	  long lVal = 0;
	  for (int idx = 0; idx < 8; idx++) {
		  lVal += (longBytes[idx] << (56 - idx * 8));
	  }		  
	  return lVal;
	}
}
