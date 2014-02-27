package op.sample.random;

public class RandomThread extends Thread {
	private int id;
	private RandomGenerator rg;
	
	public RandomThread(int id, RandomGenerator rg) {
		this.id = id;
		this.rg = rg;
	}
	
    public void run() {
		for (int i = 0; i < rg.getNumberRamge(); i++) {
			System.out.println("Thread" + id + " : " + rg.getRandom());
		}
    } 
}
