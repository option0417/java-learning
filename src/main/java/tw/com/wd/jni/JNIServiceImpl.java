package tw.com.wd.jni;

import java.util.List;

public class JNIServiceImpl implements JNIService {
	private native void invokeJNI(Object[] dataObjList);

	@Override
	public void showDataObj(List<DataObj> dataObjList) {
		invokeJNI(dataObjList.toArray());
	}

	static {
		System.loadLibrary("JNIService");
	}
}
