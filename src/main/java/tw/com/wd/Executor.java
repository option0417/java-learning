package tw.com.wd;

public class Executor {

	public static void main(String[] args) throws Throwable {
	    byte[] b = toBytes(-1);

        System.out.printf("Length: %d\n", b.length);
        System.out.printf("1: %d\n", (int) (b[0] & 0xFF));
        System.out.printf("2: %d\n", (int) (b[1] & 0xFF));
        System.out.printf("3: %d\n", (int) (b[2] & 0xFF));
        System.out.printf("4: %d\n", (int) (b[3] & 0xFF));
	}

    public static byte[] toBytes(int val) {
        byte [] b = new byte[4];
        for(int i = 3; i > 0; i--) {
            b[i] = (byte) val;
            val >>>= 8;
        }
        b[0] = (byte) val;
        return b;
    }
}

