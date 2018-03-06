package tw.com.wd;

import tw.com.wd.obj.AObj;
import tw.com.wd.obj.BObj;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Executor {

	public static void main(String[] args) throws Throwable {
        AObj a = new AObj();
        a.setId(1);
        a.setName("NameA");

        BObj b = new BObj();
        b.setId(2);
        b.setName("NameB");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);


        System.out.printf("Size: %d\n", bos.size());

        oos.writeObject(b);
        oos.close();
        bos.close();

        System.out.printf("Size: %d\n", bos.size());


        byte[] objBytes = bos.toByteArray();

        ByteArrayInputStream bis = new ByteArrayInputStream(objBytes);
        ObjectInputStream ois = new ObjectInputStream(bis);

        Object obj = ois.readObject();

        System.out.printf("Class: %s\n", obj.getClass());

        BObj bb = (BObj) obj;

        System.out.println(bb.getId());
        System.out.println(bb.getName());
        System.out.println(bb.getaObj().getId());
        System.out.println(bb.getaObj().getName());
	}
}

