package op.sample.jni;

import java.util.Random;
import java.util.UUID;

public class DataObjFactory {
	public static DataObj build() {
		return new DataObj()
			.setUserID(UUID.randomUUID().toString())
			.setAccessToken(UUID.randomUUID().toString())
			.setUserName("User-" + new Random().nextInt(10000))
			.setUserAvatar("http://127.0.0.1/user/" + UUID.randomUUID().toString())
			.setGroupID(UUID.randomUUID().toString())
			.setGroupName("Group-" + new Random().nextInt(10000))
			.setGroupAvatar("http://127.0.0.1/group/" + UUID.randomUUID().toString())
			.setPhone("0912345678")
			.setCreateTime(System.currentTimeMillis())
			.setUpdateTime(System.currentTimeMillis());
	}
}
